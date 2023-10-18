package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyPlanService {

  @Value("${tmdb.api.key}")
  private String apiKey;

  @Value("https://api.themoviedb.org/3/")
  private String rootUrl;

  MyPlanEntryRepository myPlanEntryRepository;

  RestTemplate restTemplate;

  @Autowired
  public MyPlanService(MyPlanEntryRepository myPlanEntryRepository, RestTemplate restTemplate) {
    this.myPlanEntryRepository = myPlanEntryRepository;
    this.restTemplate = restTemplate;
  }

  public List<Production> getAllProductionsInPlan() {
    return getProductions(myPlanEntryRepository.findAll());
  }

  public List<Production> getProductionsOnDate(Date date) {
    return getProductions(myPlanEntryRepository.findMyPlanEntriesByDate(date));
  }

  public List<Production> getProductions(List<MyPlanEntry> myPlanEntries) {
    List<Production> productions = new ArrayList<>();
    for(MyPlanEntry myPlanEntry : myPlanEntries) {
      String productionId = myPlanEntry.getProductionId();
      if(myPlanEntry.isMovie()) {
        Movie movie = restTemplate.getForObject(rootUrl + "movie/" + productionId + "?api_key=" + apiKey, Movie.class);
        assert movie != null;
        movie.setMedia_type("movie");
        productions.add(movie);
      } else {
        Series series = restTemplate.getForObject(rootUrl + "tv/" + productionId + "?api_key=" + apiKey, Series.class);
        assert series != null;
        series.setMedia_type("tv");
        productions.add(series);
      }
    }
    return productions;
  }

  public MyPlanEntry addEntry(MyPlanEntry myPlanEntry) {
    return myPlanEntryRepository.save(myPlanEntry);
  }

  public List<MyPlanEntry> getAllEntries() {
    return myPlanEntryRepository.findAll();
  }

}
