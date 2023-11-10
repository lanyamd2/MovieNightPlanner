package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyProviderEntry;
import com.sparta.projectmovie1.movienightplanner.models.Provider;
import com.sparta.projectmovie1.movienightplanner.models.ProviderList;
import com.sparta.projectmovie1.movienightplanner.repositories.MyProviderEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${justwatch.api.key}")
    private String justwatchApiKey;

    private RestTemplate restTemplate;

    private MyProviderEntryRepository myProviderEntryRepo;

    @Autowired
    public ProviderService(RestTemplate restTemplate,MyProviderEntryRepository myProviderEntryRepo) {
        this.restTemplate = restTemplate;
        this.myProviderEntryRepo=myProviderEntryRepo;
    }


    public List<Provider> getAllProvidersFromTmdb(String productionType){
        ProviderList providerList= restTemplate.getForObject("https://api.themoviedb.org/3/watch/providers/"+productionType+"?watch_region=GB&api_key=" + tmdbApiKey, ProviderList.class);
        System.out.println(providerList.getResults());
        return providerList.getResults();
    }


    public MyProviderEntry addToMyProviderEntries(MyProviderEntry myProviderEntry){
        return myProviderEntryRepo.save(myProviderEntry);
    }

    public List<Provider> getCurrentProviders(String userId){

        List<MyProviderEntry> myProviderEntryList=myProviderEntryRepo.findByUserId(userId);
        System.out.println("Number of providers from database--"+myProviderEntryList.size());

        List<Provider> movieProviders=getAllProvidersFromTmdb("movie");
        List<Provider> tvProviders=getAllProvidersFromTmdb("tv");

        List<Provider> currentProviders=new ArrayList<>();

        for(MyProviderEntry entry:myProviderEntryList){
            System.out.println("providerId of current entry--"+entry.getProviderId());
            List<Provider> providersTv=tvProviders.stream().filter(p->{
                /*System.out.println("id from api--"+p.getProvider_id());
                System.out.println("id from entry--"+entry.getProviderId());*/
                return p.getProvider_id()== entry.getProviderId();}).collect(Collectors.toList());
            System.out.println("size of providersTv--"+providersTv.size());
            System.out.println("here1");
            if(providersTv.size()>0){
                System.out.println("here2");
                currentProviders.add(providersTv.get(0));
                System.out.println("here3");
                continue;
            }
            else{
                System.out.println("here4");
                List<Provider> providersMovie=movieProviders.stream().filter(p->p.getProvider_id()== entry.getProviderId()).collect(Collectors.toList());
                if(providersMovie.size()>0){
                    currentProviders.add(providersMovie.get(0));
                }

            }
        }

        return currentProviders;

    }


    public void deleteProvider(Integer providerId,String userId){
            MyProviderEntry providerEntry=myProviderEntryRepo.findByUserIdAndProviderId(userId,providerId);
            myProviderEntryRepo.deleteById(providerEntry.get_id());
    }
}
