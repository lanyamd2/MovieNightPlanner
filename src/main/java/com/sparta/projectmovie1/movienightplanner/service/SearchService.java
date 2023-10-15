package com.sparta.projectmovie1.movienightplanner.service;

import com.sparta.projectmovie1.movienightplanner.model.GenreList;
import com.sparta.projectmovie1.movienightplanner.model.ProductionList;
import com.sparta.projectmovie1.movienightplanner.model.TrendingProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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

    public List<TrendingProduction> getTrendingproductions(String timeWindow){
        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/"+timeWindow+"?language=en-US&api_key="+tmdbApiKey,ProductionList.class);
        return productionList.getResults();

    }

    public List<TrendingProduction> getAllSearchResults(String searchQuery,String productionType,Integer searchGenre) {
        {
            List<TrendingProduction> finalProductionList = null;

            if (searchQuery != null && !searchQuery.equals("")) {

                if (productionType == null) {

                    ProductionList movieList = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query=" + searchQuery + "&api_key=" + tmdbApiKey, ProductionList.class);
                    ProductionList tvList = restTemplate.getForObject("https://api.themoviedb.org/3/search/tv?query=" + searchQuery + "&api_key=" + tmdbApiKey, ProductionList.class);
                    List<TrendingProduction> finalList = movieList.getResults();
                    finalList.addAll(tvList.getResults());

                    if (searchGenre != null) {
                        finalProductionList = finalList.stream().filter(p -> p.getGenres().contains(searchGenre)).collect(Collectors.toList());
                    }

                } else {
                    ProductionList productionList = restTemplate.getForObject("https://api.themoviedb.org/3/search/" + productionType + "?query=" + searchQuery + "&api_key=" + tmdbApiKey, ProductionList.class);
                    List<TrendingProduction> finalList = productionList.getResults();
                    if (searchGenre != null) {
                        finalProductionList = finalList.stream().filter(p -> p.getGenres().contains(searchGenre)).collect(Collectors.toList());
                    }
                }

            } else {

                if (productionType != null) {
                    if (searchGenre != null) {
                        System.out.println("Finding genre name");
                        GenreList genreList = restTemplate.getForObject("https://api.themoviedb.org/3/genre/" + productionType + "/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
                        String genreName = genreList.getGenres().stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();
                        System.out.println("Discover movie");
                        System.out.println(productionType+"-----------------"+genreName);
                        ProductionList productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + genreName + "&api_key=" + tmdbApiKey, ProductionList.class);
                        finalProductionList = productionList.getResults();
                    } else {
                        //what to show with only TV or Movie selected?
                    }
                } else {
                    if (searchGenre != null) {
                        //Only genre selected, Show both TV and movie in that genre

                        GenreList genreListForMovies = restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
                        String genreNameForMovie = genreListForMovies.getGenres().stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();
                        ProductionList movieList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?with_genres=" + genreNameForMovie + "&api_key=" + tmdbApiKey, ProductionList.class);

                        GenreList genreListForTv = restTemplate.getForObject("https://api.themoviedb.org/3/genre/tv/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
                        String genreNameForTv = genreListForMovies.getGenres().stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();
                        ProductionList tvList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/tv?with_genres=" + genreNameForTv + "&api_key=" + tmdbApiKey, ProductionList.class);

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

    public List<TrendingProduction> sortResultByPopularity(List<TrendingProduction> results){

        results.stream().forEach(p-> System.out.println(Double.valueOf(String.valueOf(p.getPopularity()))));

        List<TrendingProduction> sortedResults=
               results.stream().sorted((p2,p1)->Double.compare(Double.valueOf(String.valueOf(p1.getPopularity())),Double.valueOf(String.valueOf(p2.getPopularity())))).collect(Collectors.toList());
        sortedResults.stream().forEach(p-> System.out.println(Double.valueOf(String.valueOf(p.getPopularity()))));

        return sortedResults;
    }
}
