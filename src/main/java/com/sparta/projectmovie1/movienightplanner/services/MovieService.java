package com.sparta.projectmovie1.movienightplanner.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.projectmovie1.movienightplanner.models.Offer;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Crew;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.ProductionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MovieService {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${justwatch.api.key}")
    private String justWatchApiKey;
    private final WebClient webClient;

    @Autowired
    public MovieService(WebClient webClient){
        this.webClient = webClient;
    }

    public String getTmdbUrl(String id, String type){
        return "https://api.themoviedb.org/3/"+type+"/"+id+"?api_key="+tmdbApiKey;
    }

    public Movie getMovieById(String id){
        Optional<Movie> tmdbMovie = fetchTmdbMovieById(id, "movie").blockOptional();
        if(tmdbMovie.isEmpty()) throw new ProductionNotFoundException("Movie not found");
        tmdbMovie.get().setMedia_type("movie");

        String title = tmdbMovie.get().getName().toLowerCase();
        int releaseYear = setProductionOffers(tmdbMovie.get(), title);
        tmdbMovie.get().setReleaseYear(releaseYear);
        List<Crew> directors = fetchDirectors(id);

        tmdbMovie.get().setCrew(directors);

        return tmdbMovie.get();
    }

    public int setProductionOffers(Production production, String title) {
        String releaseYear="";
        if(production.getReleaseDate().isEmpty()){
            production.setOffers(new ArrayList<Offer>());
        }else{
            releaseYear = getReleaseYearFromReleaseDate(production);
            try {
                production.setOffers(fetchJustWatchOffers(title, production.getMedia_type(), releaseYear));
            }catch (ProductionNotFoundException e){
                production.setOffers(new ArrayList<Offer>());
            }
        }
        return Integer.parseInt(releaseYear);
    }

    public Mono<Movie> fetchTmdbMovieById(String id, String type){
        String url = getTmdbUrl(id, type);
        return webClient.get()
                .uri(url)
                .exchangeToMono(this::handleMovieResponse);
    }

    private Mono<Movie> handleMovieResponse(ClientResponse response) {
        if(response.statusCode().is2xxSuccessful()){
            return response.bodyToMono(Movie.class);
        }
        else if(response.statusCode().is4xxClientError()){
            return Mono.error(new ProductionNotFoundException("Movie not found"));
        }
        else if(response.statusCode().is5xxServerError()) {
            return Mono.error(new RuntimeException("Server error"));
        }
        else{
            return Mono.error(new RuntimeException("Unexpected error"));
        }
    }

    public String getJustWatchUrl(String title, String type, String releaseYear, String userLocale){
        return "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/"+type+"/locale/"+userLocale+"?title="+title+" &release_year="+releaseYear+"&token="+ justWatchApiKey;
    }

    public String getReleaseYearFromReleaseDate(Production production) {
        String releaseYear;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(production.getReleaseDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        releaseYear = df.format(date);
        return releaseYear;
    }

    public List<Offer> fetchJustWatchOffers(String title, String type, String releaseYear, String userLocale){
        String url = getJustWatchUrl(title, type, releaseYear, userLocale);
        ObjectMapper mapper = new ObjectMapper();
        return webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response-> response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new ProductionNotFoundException("Production not found on JustWatch API: "+error))))
                .bodyToMono(JsonNode.class)
                .map(s->s.findValue("offers"))
                .map(s->{
                    try{
                        if(mapper.readValue(s.traverse(), new TypeReference<List<Offer>>(){})==null) {
                            return new ArrayList<Offer>();
                        }
                        return mapper.readValue(s.traverse(), new TypeReference<List<Offer>>(){});
                    }catch (IOException e){
                        e.printStackTrace();//log info
                        return new ArrayList<Offer>();
                    }
                })
               .block();
    }

    public List<Crew> fetchDirectors(String id){
        String url = "https://api.themoviedb.org/3/movie/"+id+"/credits?api_key="+tmdbApiKey;
        ObjectMapper mapper = new ObjectMapper();
        List<Crew> crew = webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new ProductionNotFoundException("Production credits could not be found: "+error))))
                .bodyToMono(JsonNode.class)
                .map(s->s.findValue("crew"))
                .map(s->{
                    try{
                        if(mapper.readValue(s.traverse(), new TypeReference<List<Crew>>() {}) == null){
                            return new ArrayList<Crew>();
                        }
                        return mapper.readValue(s.traverse(), new TypeReference<List<Crew>>() {});
                    }catch (IOException e){
                        e.printStackTrace();
                        return new ArrayList<Crew>();
                    }
                }).block();
        if (crew == null) {
            return new ArrayList<Crew>();
        }
        return crew.stream().filter(s-> s.getJob().equalsIgnoreCase("Director")).collect(Collectors.toList());
    }

    public List<Offer> fetchJustWatchOffers(String title, String type,String releaseYear){
        return fetchJustWatchOffers(title, type, releaseYear, "en_GB");
    }
}
