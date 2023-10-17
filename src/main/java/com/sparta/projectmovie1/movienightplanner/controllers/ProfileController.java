package com.sparta.projectmovie1.movienightplanner.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sparta.projectmovie1.movienightplanner.models.Offer;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.services.MovieService;
import com.sparta.projectmovie1.movienightplanner.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ProfileController {
    private final MovieService movieService;
    private final SeriesService seriesService;

    @Autowired
    public ProfileController(MovieService movieService, SeriesService seriesService){
        this.movieService = movieService;
        this.seriesService = seriesService;
    }

    @RequestMapping("/details/movie/{id}")
    public Movie getMovieById(@PathVariable("id") String id){
        return movieService.getMovieById(id);
    }

//    @RequestMapping("details/show/{id}")
//    public Series getSeriesById(@PathVariable("id") String id){
//        return seriesService.getSeriesById(id);
//    }

    @RequestMapping("/details/movie/justwatch/{id}")
    public List<Offer> getjustMovieById(@PathVariable("id") String id){
        return movieService.fetchJustWatchOffers(id,"movie");
    }

//    @RequestMapping("details/show/justwatch/{id}")
//    public List<Offer> getjustSeriesById(@PathVariable("id") String id){
//        return movieService.fetchJustWatchOffers(id, "show");
//    }

    @RequestMapping("/details/movie/tmdb/{id}")
    public Mono<Movie> gettmdbMovieById(@PathVariable("id") String id){
        return movieService.fetchTmdbMovieById(id,"movie");
    }

    @RequestMapping("/details/series/tmdb/{id}")
    public Mono<Series> gettmdbSeriesById(@PathVariable("id") String id){
        return seriesService.fetchTmdbSeriesById(id, "tv");
    }

}
