package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.*;
import com.sparta.projectmovie1.movienightplanner.services.SearchService;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.EmptysearchResultException;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.InvalidPageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SearchController {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${justwatch.api.key}")
    private String justwatchApiKey;

    private final RestTemplate restTemplate;


    SearchService searchService;

    @Autowired
    public SearchController(RestTemplate restTemplate,SearchService searchService) {

        this.restTemplate = restTemplate;
        this.searchService=searchService;
    }


    @GetMapping("/trending")
    public List<Production> showIndexPage(@RequestParam(required = false) String timeWindow,
                                @RequestParam(required = false) String sortBy
                                ){

        if(timeWindow==null){
            timeWindow="day";
        }


        List<Production> trendingProductions=searchService.getTrendingproductionsNew(timeWindow);

        if(sortBy!=null && sortBy.equals("popularity")){
            trendingProductions=searchService.sortResultByPopularityNew(trendingProductions);
        }

        return trendingProductions;

    }

    @RequestMapping("/searchresults")
    public List<Production> getAllSearchResults(@RequestParam(required = false) String searchQuery,@RequestParam(required = false) String productionType,@RequestParam(required = false) Integer searchGenre){
        {
            List<Production> finalProductionList=null;

            if(searchQuery!=null){

                if(productionType==null){

                    ProductionList movieList=restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query="+searchQuery+"&api_key="+tmdbApiKey, ProductionList.class);
                    ProductionList tvList=restTemplate.getForObject("https://api.themoviedb.org/3/search/tv?query="+searchQuery+"&api_key="+tmdbApiKey, ProductionList.class);
                    List<Production> finalList=movieList.getResults();
                    finalList.addAll(tvList.getResults());

                    if(searchGenre!=null){
                        finalProductionList=finalList.stream().filter(p->p.getGenre_ids().contains(searchGenre)).collect(Collectors.toList());
                    }

                }
                else{
                    ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/search/"+productionType+"?query="+searchQuery+"&api_key="+tmdbApiKey, ProductionList.class);
                    List<Production> finalList=productionList.getResults();
                    if(searchGenre!=null){
                        finalProductionList=finalList.stream().filter(p->p.getGenre_ids().contains(searchGenre)).collect(Collectors.toList());
                    }
                }

            }
            else{

                if(productionType!=null){
                    if(searchGenre!=null){
                        System.out.println("Finding genre name");
                        GenreList genreList=restTemplate.getForObject("https://api.themoviedb.org/3/genre/"+productionType+"/list"+"?&api_key="+tmdbApiKey,GenreList.class);
                        String genreName=genreList.getGenres().stream().filter(g->g.getId()==searchGenre).findFirst().get().getName();
                        System.out.println("Discover movie");
                        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/discover/"+productionType+"?with_genres="+genreName+"&api_key="+tmdbApiKey, ProductionList.class);
                        finalProductionList=productionList.getResults();
                    }
                    else{
                        //what to show with only TV or Movie selected?
                    }
                }
                else{
                    if(searchGenre!=null){
                        //Only genre selected, Show both TV and movie in that genre

                        GenreList genreListForMovies=restTemplate.getForObject("https://api.themoviedb.org/3/genre/movie/list"+"?&api_key="+tmdbApiKey,GenreList.class);
                        String genreNameForMovie=genreListForMovies.getGenres().stream().filter(g->g.getId()==searchGenre).findFirst().get().getName();
                        ProductionList movieList=restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?with_genres="+genreNameForMovie+"&api_key="+tmdbApiKey, ProductionList.class);

                        GenreList genreListForTv=restTemplate.getForObject("https://api.themoviedb.org/3/genre/tv/list"+"?&api_key="+tmdbApiKey,GenreList.class);
                        String genreNameForTv=genreListForMovies.getGenres().stream().filter(g->g.getId()==searchGenre).findFirst().get().getName();
                        ProductionList tvList=restTemplate.getForObject("https://api.themoviedb.org/3/discover/tv?with_genres="+genreNameForTv+"&api_key="+tmdbApiKey, ProductionList.class);

                         finalProductionList=movieList.getResults();
                         finalProductionList.addAll(tvList.getResults());


                    }
                    else{
                        //What to show when nothing selected
                    }

                }
            }

            return finalProductionList;

        }
    }


    @GetMapping("/search-results")
    public List<Production> showResultsPageNew(@RequestParam(required = false) String searchQuery,
                                     @RequestParam(required = false) String productionType,
                                     @RequestParam(required = false) Integer searchGenre,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) String sortBy){


        if(page==null || page==0){
            page=1;
        }
        LastSearchCriteria lastSearchCriteria=new LastSearchCriteria(searchQuery,productionType,searchGenre);

        ProductionList productionList=searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page);

        if(productionList==null){
            throw new EmptysearchResultException("Search criteria didnt match any item");
        }
        List<Production> productions=productionList.getResults();

        if(sortBy!=null && sortBy.equals("popularity")){
            productions=searchService.sortResultByPopularityNew(productions);
        }

        return productions;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(EmptysearchResultException emptysearchResultException){
        return new ResponseEntity<>(emptysearchResultException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidPageException(InvalidPageException invalidPageException){
        return new ResponseEntity<>(invalidPageException.getMessage(), HttpStatus.BAD_REQUEST);
    }




}
