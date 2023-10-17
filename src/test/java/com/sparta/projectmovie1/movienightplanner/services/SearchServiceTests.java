package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.ProductionList;
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

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Test
    public void getTrendingproductionsNewTest(){
        String timeWindow="day";
        Production p1=new Production();
        Production p2=new Production();
        List<Production> productions=new ArrayList<>();
        productions.add(p1);
        productions.add(p2);
        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/trending/all/"+timeWindow+"?language=en-US&api_key="+tmdbApiKey, ProductionList.class)).thenReturn(productionList);

        Assertions.assertEquals(2,searchService.getTrendingproductionsNew("day").size());
    }


    @Test
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
}
