package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.loginconfig.SecurityUser;
import com.sparta.projectmovie1.movienightplanner.models.*;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.InvalidGenreIdException;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.TmDbApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    private SeriesService seriesService;

    private ProviderService providerService;

    @Autowired
    public SearchService(RestTemplate restTemplate,
                         MovieService movieService,
                         SeriesService seriesService,
                         ProviderService providerService) {
        this.restTemplate = restTemplate;
        this.movieService=movieService;
        this.seriesService=seriesService;
        this.providerService=providerService;
    }



    public List<Production> getTrendingproductionsNew(String timeWindow){

        ProductionList productionList=restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/"+timeWindow+"?language=en-US&api_key="+tmdbApiKey, ProductionList.class);
        List<Production> trending=productionList.getResults();
        for(Production production:trending){

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

    public ProductionList getAllSearchResults(String searchQuery, String productionType, Integer searchGenre, Integer page, SecurityUser user) {

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

            if(searchGenre!=0){
                finalProductionList = finalList.stream().filter(p -> p.getGenre_ids().contains(searchGenre)).collect(Collectors.toList());
            }
            else{
                finalProductionList=finalList;
            }

            //finalProductionList.forEach(p->p.setReleaseYear(Integer.parseInt(movieService.getReleaseYearFromReleaseDate(p))));
            /*------------Set production offers here----------------*/
            for(Production production:finalProductionList){

                if(production.getMedia_type().equals("movie")){
                    production.setReleaseYear(movieService.setProductionOffers(production,production.getName().toLowerCase()));
                }
                else{

                    production.setMedia_type("show");
                    production.setReleaseYear(movieService.setProductionOffers(production,production.getName().toLowerCase()));
                    production.setMedia_type("tv");

                }

            }

            productionListObj=new ProductionList(productionList.getPage(),finalProductionList, productionList.getTotal_pages());

        }
        else{

           /* GenreList genreList = getGenreList(productionType);

            String genreName=null;
            try{
                genreName= getGenreName(genreList.getGenres(),searchGenre);
            }catch (InvalidGenreIdException exception){
                throw exception;
            }*/


            //System.out.println(productionType+"-----------------"+genreName);
            String watchProviderSearch=null;
            if(user!=null){
                 watchProviderSearch=getStreamingSearchString(productionType,user);
                //System.out.println("watch provider search ---"+watchProviderSearch);
            }



            ProductionList productionList=null;

            try{
                if(user!=null && watchProviderSearch.length()>0){
                    if(searchGenre!=0){
                        productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + String.valueOf(searchGenre) + "&page="+page+"&api_key=" + tmdbApiKey+watchProviderSearch, ProductionList.class);
                    }
                    else{
                        productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?page="+page+"&api_key=" + tmdbApiKey+watchProviderSearch, ProductionList.class);
                    }

                }
                else{
                    if(searchGenre!=0){
                        productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + String.valueOf(searchGenre) + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class);
                    }
                    else{
                        productionList = restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?page="+page+"&api_key=" + tmdbApiKey, ProductionList.class);
                    }

                }


            }catch(Exception exception){

                throw new TmDbApiException(exception.getMessage());
            }


            finalProductionList = productionList.getResults();
            finalProductionList.forEach(p->p.setMedia_type(productionType));
            //finalProductionList.forEach(p->p.setReleaseYear(Integer.parseInt(movieService.getReleaseYearFromReleaseDate(p))));

            /*---------------1st approch------------------*/
            for(Production production:finalProductionList){

            if(production.getMedia_type().equals("movie")){
                production.setReleaseYear(movieService.setProductionOffers(production,production.getName().toLowerCase()));
            }
            else{

                production.setMedia_type("show");
                production.setReleaseYear(movieService.setProductionOffers(production,production.getName().toLowerCase()));
                production.setMedia_type("tv");

            }

            }

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


    public String getStreamingSearchString(String productionType,SecurityUser securityUser){

        String userId = securityUser.getUser().getId();

        String streamingProvider="";

        List<Provider> providers=providerService.getCurrentProviders(userId);
        List<Provider> prouctionTypeProviders=providerService.getAllProvidersFromTmdb(productionType);

        List<Provider> providersForTheProductionType=new ArrayList<>();

        for(Provider provider:providers){

            int size=prouctionTypeProviders.stream().filter(p->{
                String valueFromDB=String.valueOf(provider.getProvider_id());
                String valueFromApi=String.valueOf(p.getProvider_id());
                return ((p.getProvider_id()==provider.getProvider_id())||(valueFromDB.equals(valueFromApi)));}).collect(Collectors.toList()).size();
            if(size>0){
                providersForTheProductionType.add(provider);
            }
        }

        if(providersForTheProductionType.size()>0){
            streamingProvider=streamingProvider+"&watch_region=GB&with_watch_providers=";
            int i=0;
            for(Provider provider:providersForTheProductionType){
                i++;
                if(i<providersForTheProductionType.size()){
                    streamingProvider=streamingProvider+String.valueOf(provider.getProvider_id())+"|";
                }
                else{
                    streamingProvider=streamingProvider+String.valueOf(provider.getProvider_id());
                }
            }
        }


        return streamingProvider;
    }


}
