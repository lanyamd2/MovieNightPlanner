package com.sparta.projectmovie1.movienightplanner.controller;

import com.sparta.projectmovie1.movienightplanner.model.Production;
import com.sparta.projectmovie1.movienightplanner.model.ProductionList;
import com.sparta.projectmovie1.movienightplanner.model.TrendingProduction;
import com.sparta.projectmovie1.movienightplanner.model.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.model.tvshows.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrialController {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${justwatch.api.key}")
    private String justwatchApiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public TrialController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/movie/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        return restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + tmdbApiKey, Movie.class);

    }

    @RequestMapping("/tv/{seriesId}")
    public Series getSeriesInfo(@PathVariable("seriesId") String seriesId){
        return restTemplate.getForObject("https://api.themoviedb.org/3/tv/" + seriesId + "?api_key=" + tmdbApiKey, Series.class);

    }

    @RequestMapping("all/trending")
    public List<TrendingProduction> getTrendingproductions(){
        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/day?language=en-US&api_key="+tmdbApiKey,ProductionList.class);
        return productionList.getResults();

    }




}
