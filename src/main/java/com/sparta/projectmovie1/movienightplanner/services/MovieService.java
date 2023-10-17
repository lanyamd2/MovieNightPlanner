package com.sparta.projectmovie1.movienightplanner.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.projectmovie1.movienightplanner.models.Offer;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.ProductionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
        Movie tmdbMovie = fetchTmdbMovieById(id, "movie").block();
        if(tmdbMovie==null) throw new ProductionNotFoundException("Movie not found");
        tmdbMovie.setMedia_type("movie");
        tmdbMovie.setOffers(fetchJustWatchOffers(id,"movie"));
        return tmdbMovie;
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
        else if(response.statusCode().is5xxServerError()){
            return Mono.error(new RuntimeException("Server error"));
        }
        else{
            return Mono.error(new RuntimeException("Unexpected error"));
        }
    }

    public String getJustWatchMovieUrl(String id, String userLocale){
        Optional<Movie> movie = fetchTmdbMovieById(id,"movie").blockOptional();
        String title="";
        String releaseYear;
        if(movie.isPresent()){
            title +=movie.get().getName();//validate title
            releaseYear = getReleaseYearFromReleaseDate(movie);
        }
        else{
            throw new ProductionNotFoundException("Movie not found");
        }
        return "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/movie/locale/"+userLocale+"?title="+title+"&release_year="+releaseYear+"&token="+ justWatchApiKey;
    }

    private static String getReleaseYearFromReleaseDate(Optional<Movie> movie) {
        String releaseYear;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(movie.get().getReleaseDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        releaseYear = df.format(date);
        return releaseYear;
    }

    public String getProductionCountry(Production production){
        if(production.getProductionCountries().stream().findFirst().isEmpty()){
            throw new ProductionNotFoundException("Movie not found");
        }
        return production.getProductionCountries().stream().findFirst().get().getIso31661();
    }

    public List<Offer> fetchJustWatchOffers(String id, String type, String userLocale){
        String url = getJustWatchMovieUrl(id, userLocale); //if type is movie
        //if type is show
        //else throw exception
        ObjectMapper mapper = new ObjectMapper();
        return webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(s->s.findValue("offers"))
                .map(s->{
                    try{
                        if(mapper.readValue(s.traverse(), new TypeReference<List<Offer>>(){})==null){
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

    public List<Offer> fetchJustWatchOffers(String id, String type){
        return fetchJustWatchOffers(id, type, "en_GB");
    }


}
