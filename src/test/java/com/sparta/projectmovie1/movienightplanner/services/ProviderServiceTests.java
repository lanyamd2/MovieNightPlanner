package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyProviderEntry;
import com.sparta.projectmovie1.movienightplanner.models.Provider;
import com.sparta.projectmovie1.movienightplanner.models.ProviderList;
import com.sparta.projectmovie1.movienightplanner.repositories.MyProviderEntryRepository;
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
public class ProviderServiceTests {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Autowired
    ProviderService providerService;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    MyProviderEntryRepository myProviderEntryRepository;

    @Test
    public void getAllProvidersFromTmdbTest(){
        String productionType="movie";
        Provider p1=new Provider();
        List<Provider> pList=new ArrayList<>();
        pList.add(p1);
        ProviderList providerList=new ProviderList();
        providerList.setResults(pList);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/watch/providers/"+productionType+"?watch_region=GB&api_key=" + tmdbApiKey, ProviderList.class)).thenReturn(providerList);
        Assertions.assertEquals(1,providerService.getAllProvidersFromTmdb(productionType).size());
    }


    @Test
    public void addToMyProviderEntriesTest(){
        MyProviderEntry myProviderEntry=new MyProviderEntry();
        myProviderEntry.setUserId("dummyId");
        Mockito.when(myProviderEntryRepository.save(Mockito.any(MyProviderEntry.class))).thenReturn(myProviderEntry);
        Assertions.assertEquals("dummyId",myProviderEntryRepository.save(myProviderEntry).getUserId());
    }

    @Test
    public void getCurrentProvidersTest(){
        MyProviderEntry myProviderEntry=new MyProviderEntry();
        myProviderEntry.setProviderId(1);
        List<MyProviderEntry> myProviderEntryList=new ArrayList<>();
        myProviderEntryList.add(myProviderEntry);

        Mockito.when(myProviderEntryRepository.findByUserId(Mockito.any(String.class))).thenReturn(myProviderEntryList);

        Provider p1=new Provider();
        p1.setProvider_id(1);
        List<Provider> pList=new ArrayList<>();
        pList.add(p1);
        ProviderList providerList=new ProviderList();
        providerList.setResults(pList);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/watch/providers/"+"movie"+"?watch_region=GB&api_key=" + tmdbApiKey, ProviderList.class)).thenReturn(providerList);
        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/watch/providers/"+"tv"+"?watch_region=GB&api_key=" + tmdbApiKey, ProviderList.class)).thenReturn(providerList);

        Assertions.assertEquals(1,providerService.getCurrentProviders("dummy1").size());

    }

    @Test
    public void getSearchedProviderTest(){

        Provider p1=new Provider();
        p1.setProvider_id(1);
        p1.setProvider_name("dummy");
        List<Provider> pList=new ArrayList<>();
        pList.add(p1);
        ProviderList providerList=new ProviderList();
        providerList.setResults(pList);

        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/watch/providers/"+"movie"+"?watch_region=GB&api_key=" + tmdbApiKey, ProviderList.class)).thenReturn(providerList);
        Mockito.when(restTemplate.getForObject("https://api.themoviedb.org/3/watch/providers/"+"tv"+"?watch_region=GB&api_key=" + tmdbApiKey, ProviderList.class)).thenReturn(providerList);

        Assertions.assertEquals(1,providerService.getSearchedProvider("movie","du").size());
    }
}
