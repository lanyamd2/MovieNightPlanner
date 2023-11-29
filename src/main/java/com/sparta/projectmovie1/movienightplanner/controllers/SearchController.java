package com.sparta.projectmovie1.movienightplanner.controllers;

import com.sparta.projectmovie1.movienightplanner.models.*;
import com.sparta.projectmovie1.movienightplanner.services.SearchService;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.EmptysearchResultException;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.InvalidGenreIdException;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.InvalidProductionTypeException;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.TmDbApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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




    @GetMapping("/search-results")
    public List<Production> showResultsPageNew(@RequestParam(required = false) String searchQuery,
                                     @RequestParam String productionType,
                                     @RequestParam Integer searchGenre,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) String sortBy){

        if(!productionType.equals("movie") && !productionType.equals("tv")){
            throw new InvalidProductionTypeException("productionType should be movie or tv");
        }


        if(page==null || page==0){
            page=1;
        }
        LastSearchCriteria lastSearchCriteria=new LastSearchCriteria(searchQuery,productionType,searchGenre);

        ProductionList productionList=searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page,null);

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
    public ResponseEntity<String> handleInvalidPageException(TmDbApiException invalidPageException){
        return new ResponseEntity<>(invalidPageException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidProductionTypeException(InvalidProductionTypeException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidGenreIdException(InvalidGenreIdException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }




}
