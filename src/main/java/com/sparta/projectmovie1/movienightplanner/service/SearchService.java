package com.sparta.projectmovie1.movienightplanner.service;

import com.sparta.projectmovie1.movienightplanner.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${justwatch.api.key}")
    private String justwatchApiKey;

    private RestTemplate restTemplate;

    @Autowired
    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Production> getTrendingproductionsNew(String timeWindow){
        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/"+timeWindow+"?language=en-US&api_key="+tmdbApiKey, ProductionList.class);
        return productionList.getResults();

    }

    public List<Production> getAllSearchResults(String searchQuery, String productionType, Integer searchGenre) {
        {
            List<Production> finalProductionList = null;

            if (searchQuery != null && !searchQuery.equals("")) {

                if (productionType == null) {

                    //option not provided from front end

                    ProductionList movieList = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query=" + searchQuery + "&api_key=" + tmdbApiKey, ProductionList.class);
                    movieList.getResults().forEach(p->p.setMedia_type("movie"));
                    ProductionList tvList = restTemplate.getForObject("https://api.themoviedb.org/3/search/tv?query=" + searchQuery + "&api_key=" + tmdbApiKey, ProductionList.class);
                    tvList.getResults().forEach(p->p.setMedia_type("tv"));
                    List<Production> finalList = movieList.getResults();
                    finalList.addAll(tvList.getResults());

                    if (searchGenre != null) {
                        finalProductionList = finalList.stream().filter(p -> p.getGenre_ids().contains(searchGenre)).collect(Collectors.toList());
                    }

                } else {
                    ProductionList productionList = restTemplate.getForObject("https://api.themoviedb.org/3/search/" + productionType + "?query=" + searchQuery + "&api_key=" + tmdbApiKey, ProductionList.class);
                    List<Production> finalList = productionList.getResults();
                    finalList.forEach(p->p.setMedia_type(productionType));
                    if (searchGenre != null) {
                        finalProductionList = finalList.stream().filter(p -> p.getGenre_ids().contains(searchGenre)).collect(Collectors.toList());
                    }
                    else{
                        finalProductionList=finalList;
                    }
                }

            } else {

                if (productionType != null) {
                    if (searchGenre != null) {

                        GenreList genreList = restTemplate.getForObject("https://api.themoviedb.org/3/genre/" + productionType + "/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
                        String genreName = genreList.getGenres().stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();

                        System.out.println(productionType+"-----------------"+genreName);

                        ProductionList productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + genreName + "&api_key=" + tmdbApiKey, ProductionList.class);
                        finalProductionList = productionList.getResults();
                        finalProductionList.forEach(p->p.setMedia_type(productionType));
                    } else {
                        //option not provided from front end
                        //what to show with only TV or Movie selected - popular movies

                        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/"+productionType+"/popular?api_key="+tmdbApiKey, ProductionList.class);
                        finalProductionList=productionList.getResults();
                    }
                } else {
                    if (searchGenre != null) {
                        //Only genre selected, Show both TV and movie in that genre
                        //option not given in the front end

                        GenreList genreListForMovies = restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
                        String genreNameForMovie = genreListForMovies.getGenres().stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();
                        ProductionList movieList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?with_genres=" + genreNameForMovie + "&api_key=" + tmdbApiKey, ProductionList.class);
                        movieList.getResults().forEach(p->p.setMedia_type("movie"));

                        GenreList genreListForTv = restTemplate.getForObject("https://api.themoviedb.org/3/genre/tv/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
                        String genreNameForTv = genreListForMovies.getGenres().stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();
                        ProductionList tvList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/tv?with_genres=" + genreNameForTv + "&api_key=" + tmdbApiKey, ProductionList.class);
                        tvList.getResults().forEach(p->p.setMedia_type("tv"));
                        finalProductionList = movieList.getResults();
                        finalProductionList.addAll(tvList.getResults());


                    } else {
                        //What to show when nothing selected
                    }

                }
            }

            return finalProductionList;

        }
    }

    public List<Production> sortResultByPopularityNew(List<Production> results){

        List<Production> sortedResults=
                results.stream().sorted((p2,p1)->Double.compare(Double.valueOf(String.valueOf(p1.getPopularity())),Double.valueOf(String.valueOf(p2.getPopularity())))).collect(Collectors.toList());

        return sortedResults;
    }
}
