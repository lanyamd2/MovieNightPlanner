package com.sparta.projectmovie1.movienightplanner.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.projectmovie1.movienightplanner.models.ActiveCountry;
import com.sparta.projectmovie1.movienightplanner.models.Offer;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.ActiveCountriesNotFoundException;
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
import java.time.LocalDateTime;
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

    public Movie getMovieById(String id){
        Movie tmdbMovie = fetchTmdbMovieById(id).block();
        if(tmdbMovie==null) throw new ProductionNotFoundException("Movie not found");
        tmdbMovie.setOffers(fetchJustWatchOffers(id));
        return tmdbMovie;
    }

    public String getTmdbUrl(String id){
        return "https://api.themoviedb.org/3/movie/"+id+"?api_key="+tmdbApiKey;
    }

    public String getJustWatchUrl(String id, String userLocale){
        Optional<Movie> movie = fetchTmdbMovieById(id).blockOptional();
        String title="";
        String releaseYear;
        if(movie.isPresent()){
            title +=movie.get().getName();//validate title

            //CREATE SEPARATE METHOD
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = format.parse(movie.get().getReleaseDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            releaseYear = df.format(date);
        }
        else{
            throw new ProductionNotFoundException("Movie not found");
        }
        return "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/movie/locale/"+userLocale+"?title="+title+"&release_year="+releaseYear+"&token="+ justWatchApiKey;
    }

    public String getProductionCountry(Production production){
        if(production.getProductionCountries().stream().findFirst().isEmpty()){
            throw new ProductionNotFoundException("Movie not found");
        }
        return production.getProductionCountries().stream().findFirst().get().getIso31661();
    }

    public Mono<Movie> fetchTmdbMovieById(String id){
        String url = getTmdbUrl(id);
        return webClient.get()
                .uri(url)
                .exchangeToMono(this::handleResponse);
    }

    public List<Offer> fetchJustWatchOffers(String id, String userLocale){
        String url = getJustWatchUrl(id, userLocale);
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
                        e.printStackTrace();
                        return new ArrayList<Offer>();
                    }
                })
               .block();
    }

    public List<Offer> fetchJustWatchOffers(String id){
        return fetchJustWatchOffers(id, "en_GB");
    }

    private Mono<Movie> handleResponse(ClientResponse response) {
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
}
