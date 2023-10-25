package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.Genre;
import com.sparta.projectmovie1.movienightplanner.models.GenreList;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.ProductionList;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.InvalidGenreIdException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SearchServiceTests {

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    SearchService searchService;

    @MockBean
    MovieService movieService;

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Test
    @Description("getTrendingproductionsNew method returns correct results")
    public void getTrendingproductionsNewTest(){

        String timeWindow="day";
        Production p1=new Production();
        p1.setName("abc");
        p1.setMedia_type("movie");
        Production p2=new Production();
        p2.setName("xyz");
        p2.setMedia_type("movie");
        List<Production> productions=new ArrayList<>();
        productions.add(p1);
        productions.add(p2);
        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/"+timeWindow+"?language=en-US&api_key="+tmdbApiKey, ProductionList.class)).thenReturn(productionList);
        //Mockito.when(movieService.getReleaseYearFromReleaseDate(Mockito.any(Production.class))).thenReturn("2023");
        Mockito.when(movieService.setProductionOffers(Mockito.any(Production.class),Mockito.any(String.class))).thenReturn(2023);
        Assertions.assertEquals(2,searchService.getTrendingproductionsNew("day").size());
    }


    @Test
    @Description("sortResultByPopularityNew method returns correct results")
    public void sortResultByPopularityNewTest(){

        Production p1=new Production();
        p1.setPopularity(100.5);
        Production p2=new Production();
        p2.setPopularity(99.8);
        Production p3=new Production();
        p3.setPopularity(200.6);

        List<Production> productions=new ArrayList<>();
        productions.add(p1);
        productions.add(p2);
        productions.add(p3);

        Assertions.assertEquals(200.6,searchService.sortResultByPopularityNew(productions).get(0).getPopularity());

    }

    @Test
    @Description("sortResultByPopularityNew method returns empty list when an empty list is passed")
    public void sortResultByPopularityNewTest_PassingEmptyList(){
        List<Production> productions=new ArrayList<>();
        Assertions.assertEquals(0,searchService.sortResultByPopularityNew(productions).size());

    }

    @Test
    @Description("getGenreList method returns correct results")
    public void getGenreListTest(){

        String productionType="movie";

        GenreList genreList=new GenreList();
        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        List<Genre> genres=new ArrayList<>();
        genres.add(action);

        genreList.setGenres(genres);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/genre/" + productionType + "/list" + "?&api_key=" + tmdbApiKey, GenreList.class)).thenReturn(genreList);
        Assertions.assertEquals(1,searchService.getGenreList(productionType).getGenres().size());

    }

    @Test
    @Description("getGenreName method returns correct genre name")
    public void getGenreNameTest_IdPresentInTheList(){

        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        List<Genre> genres=new ArrayList<>();
        genres.add(action);

        Assertions.assertEquals("Action",searchService.getGenreName(genres,28));

    }

    @Test
    @Description("getGenreName method throws InvalidGenreIdException when searchGenre not present in the list of genres")
    public void getGenreNameTest_IdNotPresentIntheList(){

        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        List<Genre> genres=new ArrayList<>();
        genres.add(action);

        Assertions.assertThrows(InvalidGenreIdException.class,()->searchService.getGenreName(genres,30));

    }



    @Test
    @Description("getAllSearchResults with searchQuery and searchGenre exists")
    public void getAllSearchResultsTestWithSearchQuery_searchGenreExits(){

        String searchQuery="Mission";
        String productionType="movie";
        Integer searchGenre=28;
        Integer page=1;

        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        Genre adventure=new Genre();
        adventure.setId(30);
        adventure.setName("Adventure");

        List<Genre> genres1=new ArrayList<>();
        genres1.add(action);
        List<Integer> genre_ids1=new ArrayList<>();
        genre_ids1.add(28);

        List<Genre> genres2=new ArrayList<>();
        genres2.add(adventure);
        List<Integer> genre_ids2=new ArrayList<>();
        genre_ids2.add(30);

        Production p1=new Production();
        p1.setName("Mission Impossible");
        p1.setGenre_ids(genre_ids1);
        System.out.println(p1.getGenre_ids());

        Production p2=new Production();
        p2.setName("Mission Mars");
        p2.setGenre_ids(genre_ids2);

        List<Production> productions=new ArrayList<>();
        productions.add(p1);
        productions.add(p2);
        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);


        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/search/" + productionType + "?query=" + searchQuery + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class)).thenReturn(productionList);

        Assertions.assertEquals(1,searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page).getResults().size());
        Assertions.assertEquals("movie",searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page).getResults().get(0).getMedia_type());

    }

    @Test
    @Description("getAllSearchResults with searchQuery and searchGenre does not exist")
    public void getAllSearchResultsTestWithSearchQuery_searchGenreDoesNotExits(){

        String searchQuery="Mission";
        String productionType="movie";
        Integer searchGenre=32;
        Integer page=1;

        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        Genre adventure=new Genre();
        adventure.setId(30);
        adventure.setName("Adventure");

        List<Genre> genres1=new ArrayList<>();
        genres1.add(action);
        List<Integer> genre_ids1=new ArrayList<>();
        genre_ids1.add(28);

        List<Genre> genres2=new ArrayList<>();
        genres2.add(adventure);
        List<Integer> genre_ids2=new ArrayList<>();
        genre_ids2.add(30);

        Production p1=new Production();
        p1.setName("Mission Impossible");
        p1.setGenre_ids(genre_ids1);
        System.out.println(p1.getGenre_ids());

        Production p2=new Production();
        p2.setName("Mission Mars");
        p2.setGenre_ids(genre_ids2);

        List<Production> productions=new ArrayList<>();
        productions.add(p1);
        productions.add(p2);
        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);


        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/search/" + productionType + "?query=" + searchQuery + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class)).thenReturn(productionList);

        Assertions.assertEquals(0,searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page).getResults().size());

    }

    @Test
    @Description("getAllSearchResults method returned correct value for empty search result from api")
    public void getAllSearchResultsTestWithSearchQuery_EmptySearchResultFromApi(){

        String searchQuery="Mission";
        String productionType="movie";
        Integer searchGenre=28;
        Integer page=1;

        List<Production> productions=new ArrayList<>();
        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/search/" + productionType + "?query=" + searchQuery + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class)).thenReturn(productionList);

        Assertions.assertEquals(0,searchService.getAllSearchResults(searchQuery,productionType,searchGenre,page).getResults().size());

    }


    @Test
    public void getAllSearchResultsTestWithoutSearchQuery(){

        String productionType="movie";
        Integer searchGenre=28;
        Integer page=1;
        String genreName="Action";

        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        List<Genre> genres1=new ArrayList<>();
        genres1.add(action);
        List<Integer> genre_ids1=new ArrayList<>();
        genre_ids1.add(28);


        Production p1=new Production();
        p1.setName("Mission Impossible");
        p1.setGenre_ids(genre_ids1);
        System.out.println(p1.getGenre_ids());

        List<Production> productions=new ArrayList<>();
        productions.add(p1);

        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);


        GenreList genreList=new GenreList();
        List<Genre> genres=new ArrayList<>();
        genres.add(action);
        genreList.setGenres(genres);



        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + genreName + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class)).thenReturn(productionList);
        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/genre/" + productionType + "/list" + "?&api_key=" + tmdbApiKey, GenreList.class)).thenReturn(genreList);
        Mockito.when(movieService.getReleaseYearFromReleaseDate(Mockito.any(Production.class))).thenReturn("2023");
        Assertions.assertEquals(1,searchService.getAllSearchResults(null,productionType,searchGenre,page).getResults().size());

    }

    @Test
    public void getAllSearchResultsTestWithoutSearchQuery_InvalidSearchGenre(){

        String productionType="movie";
        Integer searchGenre=32;
        Integer page=1;
        String genreName="Action";

        Genre action=new Genre();
        action.setId(28);
        action.setName("Action");

        List<Genre> genres1=new ArrayList<>();
        genres1.add(action);
        List<Integer> genre_ids1=new ArrayList<>();
        genre_ids1.add(28);


        Production p1=new Production();
        p1.setName("Mission Impossible");
        p1.setGenre_ids(genre_ids1);
        System.out.println(p1.getGenre_ids());

        List<Production> productions=new ArrayList<>();
        productions.add(p1);

        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);


        GenreList genreList=new GenreList();
        List<Genre> genres=new ArrayList<>();
        genres.add(action);
        genreList.setGenres(genres);



        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/discover/" + productionType + "?with_genres=" + genreName + "&page="+page+"&api_key=" + tmdbApiKey, ProductionList.class)).thenReturn(productionList);
        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/genre/" + productionType + "/list" + "?&api_key=" + tmdbApiKey, GenreList.class)).thenReturn(genreList);

        Assertions.assertThrows(InvalidGenreIdException.class,()->searchService.getAllSearchResults(null,productionType,searchGenre,page));


    }

}
