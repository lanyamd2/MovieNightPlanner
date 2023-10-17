package com.sparta.projectmovie1.movienightplanner.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sparta.projectmovie1.movienightplanner.models.Offer;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ProfileController {
    private final MovieService movieService;

    @Autowired
    public ProfileController(MovieService movieService){
        this.movieService = movieService;
    }

    @RequestMapping("/details/movie/{id}")
    public Movie getMovieById(@PathVariable("id") String id){
//        return profileService.fetchTmdbMovieById(id);
        return movieService.getMovieById(id);
    }

    @RequestMapping("/details/movie/justwatch/{id}")
    public List<Offer> getjustMovieById(@PathVariable("id") String id){
        return movieService.fetchJustWatchOffers(id);
    }
@RequestMapping("/details/movie/tmdb/{id}")
    public Mono<Movie> gettmdbMovieById(@PathVariable("id") String id){
        return movieService.fetchTmdbMovieById(id);
    }



}
