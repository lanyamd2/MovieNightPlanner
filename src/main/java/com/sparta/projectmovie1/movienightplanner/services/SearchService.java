package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.*;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.InvalidGenreIdException;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.TmDbApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${justwatch.api.key}")
    private String justwatchApiKey;

    private RestTemplate restTemplate;
    private MovieService movieService;

    @Autowired
    public SearchService(RestTemplate restTemplate,MovieService movieService) {
        this.restTemplate = restTemplate;
        this.movieService=movieService;
    }



    public List<Production> getTrendingproductionsNew(String timeWindow){
        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/"+timeWindow+"?language=en-US&api_key="+tmdbApiKey, ProductionList.class);
        List<Production> trending=productionList.getResults();
        for(Production production:trending){
            /*if(production.getMedia_type().equals("movie")){
                production.setReleaseYear(Integer.parseInt(movieService.getReleaseYearFromReleaseDate(production)));
            }*/
            //production.setReleaseYear(Integer.parseInt(movieService.getReleaseYearFromReleaseDate(production)));
            if(production.getMedia_type().equals("movie")){
                production.setReleaseYear(movieService.setProductionOffers(production,production.getName().toLowerCase()));
            }
            else{

                production.setMedia_type("show");
                production.setReleaseYear(movieService.setProductionOffers(production,production.getName().toLowerCase()));
                production.setMedia_type("tv");

            }

        }
        return trending;
    }

    public List<Production> sortResultByPopularityNew(List<Production> results){

        List<Production> sortedResults=
                results.stream().sorted((p2,p1)->Double.compare(Double.valueOf(String.valueOf(p1.getPopularity())),Double.valueOf(String.valueOf(p2.getPopularity())))).collect(Collectors.toList());

        return sortedResults;
    }

    public ProductionList getAllSearchResults(String searchQuery, String productionType, Integer searchGenre, Integer page) {

        ProductionList productionListObj=null;
        List<Production> finalProductionList = null;

        if(searchQuery != null && !searchQuery.equals("")){

            ProductionList productionList=null;
            List<Production> finalList=null;
            try{

                productionList = restTemplate.getForObject("https://api.themoviedb.org/3/search/" + productionType + "?query=" + searchQuery + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class);
                finalList = productionList.getResults();
                finalList.forEach(p->p.setMedia_type(productionType));

            }catch (Exception exception){
                throw new TmDbApiException(exception.getMessage());
            }
            finalProductionList = finalList.stream().filter(p -> p.getGenre_ids().contains(searchGenre)).collect(Collectors.toList());
            productionListObj=new ProductionList(productionList.getPage(),finalProductionList, productionList.getTotal_pages());

        }
        else{

            GenreList genreList = getGenreList(productionType);

            String genreName=null;
            try{
                genreName= getGenreName(genreList.getGenres(),searchGenre);
            }catch (InvalidGenreIdException exception){
                throw exception;
            }


            System.out.println(productionType+"-----------------"+genreName);
            ProductionList productionList=null;

            try{
                 productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + genreName + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class);

            }catch(Exception exception){

                throw new TmDbApiException(exception.getMessage());
            }


            finalProductionList = productionList.getResults();
            finalProductionList.forEach(p->p.setMedia_type(productionType));
            finalProductionList.forEach(p->p.setReleaseYear(Integer.parseInt(movieService.getReleaseYearFromReleaseDate(p))));
            productionListObj=new ProductionList(productionList.getPage(),finalProductionList, productionList.getTotal_pages());

        }

        return productionListObj;
    }

    public GenreList getGenreList(String productionType){
        return restTemplate.getForObject("https://api.themoviedb.org/3/genre/" + productionType + "/list" + "?&api_key=" + tmdbApiKey, GenreList.class);
    }

    public String getGenreName(List<Genre> genres,Integer searchGenre){
        String genreName=null;
        try{
             genreName=genres.stream().filter(g -> g.getId() == searchGenre).findFirst().get().getName();

        }catch(NoSuchElementException exception){
            throw new InvalidGenreIdException("Invalid searchGenre - searchGenre does not exist");
        }

        return genreName;
    }



}
