package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPlanService {
  MyPlanEntryRepository myPlanEntryRepository;

  MovieService movieService;

  @Autowired
  public MyPlanService(MyPlanEntryRepository myPlanEntryRepository, MovieService movieService) {
    this.myPlanEntryRepository = myPlanEntryRepository;
    this.movieService = movieService;
  }

  public List<Production> getProductionsOnDate(Date date) {
    List<Production> productions = new ArrayList<>();
    for(MyPlanEntry myPlanEntry : myPlanEntryRepository.findMyPlanEntriesByDate(date)) {
      String productionId = myPlanEntry.getProductionId();
      if(myPlanEntry.isMovie()) {
        Movie movie = movieService.getMovieById(productionId);
        movie.setMedia_type("movie");
        productions.add(movie);
      }
//      else {
//        productions.add(seriesService.getSeriesById(productionId));
//      }
    }
    return productions;
  }

  public List<Production> getAllProductionsInPlan() {
    List<Production> productions = new ArrayList<>();
    for(MyPlanEntry myPlanEntry : myPlanEntryRepository.findAll()) {
      String productionId = myPlanEntry.getProductionId();
      if(myPlanEntry.isMovie()) {
        Movie movie = movieService.getMovieById(productionId);
        movie.setMedia_type("movie");
        productions.add(movie);
      }
//      else {
//        productions.add(seriesService.getSeriesById(productionId));
//      }
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
