package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.time.DateUtils;
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

  public Map<Date, List<Production>> getAllProductionsWithDatesInPlan(String userId) {
    List<MyPlanEntry> entries = myPlanEntryRepository.findMyPlanEntriesByUserIdAndDateGreaterThanEqual(userId,
        DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
    Map<Date, List<Production>> entriesByDate = new TreeMap<>();
    for(MyPlanEntry entry : entries) {
      Date date = entry.getDate();
      Production production = getProduction(entry);
      entriesByDate.computeIfAbsent(date, k -> new ArrayList<>());
      entriesByDate.get(date).add(production);
    }
    return entriesByDate;
  }

  public List<Production> getProductionsOnDate(String userId, Date date) {
    return getProductions(myPlanEntryRepository.findMyPlanEntriesByUserIdAndDate(userId, date));
  }

  public List<Production> getProductions(List<MyPlanEntry> myPlanEntries) {
    List<Production> productions = new ArrayList<>();
    for(MyPlanEntry myPlanEntry : myPlanEntries) {
      productions.add(getProduction(myPlanEntry));
    }
    return productions;
  }

  public Production getProduction(MyPlanEntry entry) {
    int productionId = entry.getProductionId();
    if(entry.isMovie()) {
      Movie movie = restTemplate.getForObject(rootUrl + "movie/" + productionId + "?api_key=" + apiKey, Movie.class);
      assert movie != null;
      movie.setMedia_type("movie");
      return movie;
    } else {
      Series series = restTemplate.getForObject(rootUrl + "tv/" + productionId + "?api_key=" + apiKey, Series.class);
      assert series != null;
      series.setMedia_type("tv");
      return series;
    }
  }

  public void deleteEntry(String userId, Integer productionId, Date date) {
    myPlanEntryRepository.deleteMyPlanEntryByUserIdAndProductionIdAndDate(userId, productionId, date);
  }
}
