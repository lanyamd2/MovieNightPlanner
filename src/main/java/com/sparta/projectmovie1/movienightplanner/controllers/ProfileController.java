package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.Offer;
import com.sparta.projectmovie1.movienightplanner.models.movies.Crew;
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
@RequestMapping("/api")
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

    @RequestMapping("details/tv/{id}")
    public Series getSeriesById(@PathVariable("id") String id){
        return seriesService.getSeriesById(id);
    }

    @RequestMapping("/details/justwatch/{title}/{type}/{releaseYear}")
    public List<Offer> getjustMovieById(@PathVariable("title") String title,
                                        @PathVariable("type") String type,
                                        @PathVariable("releaseYear") String releaseYear
                                        ){
        return movieService.fetchJustWatchOffers(title,type,releaseYear);
    }

    @RequestMapping("/details/movie/tmdb/{id}")
    public Mono<Movie> gettmdbMovieById(@PathVariable("id") String id){
        return movieService.fetchTmdbMovieById(id,"movie");
    }

    @RequestMapping("/details/tv/tmdb/{id}")
    public Mono<Series> gettmdbSeriesById(@PathVariable("id") String id){
        return seriesService.fetchTmdbSeriesById(id, "tv");
    }

    @RequestMapping("/details/movie/directors/{id}")
    public List<Crew> gettmdbMovieDirectors(@PathVariable("id") String id){
        return movieService.fetchDirectors(id);
    }

}
