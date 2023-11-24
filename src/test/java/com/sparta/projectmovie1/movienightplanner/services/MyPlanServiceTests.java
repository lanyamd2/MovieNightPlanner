package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class MyPlanServiceTests {

  @Autowired
  private MyPlanService myPlanService;

  @MockBean
  private MyPlanEntryRepository myPlanEntryRepository;

  @MockBean
  private RestTemplate restTemplate;

  @Value("${tmdb.api.key}")
  private String apiKey;

  @Value("https://api.themoviedb.org/3/")
  private String rootUrl;
  
  private final MyPlanEntry filmEntry = new MyPlanEntry("testID", 603, true, Date.valueOf("2023-10-27"));
  private final MyPlanEntry tvEntry = new MyPlanEntry("testID2", 1396, false, Date.valueOf("2023-10-29"));

  @Test
  public void MyPlanService_AddEntry_SuccessfullySaves() {
    Mockito.when(myPlanEntryRepository.save(Mockito.any(MyPlanEntry.class))).thenReturn(filmEntry);
    MyPlanEntry savedEntry = myPlanService.addEntry(filmEntry);
    Assertions.assertThat(savedEntry).isNotNull().isEqualTo(filmEntry);
  }

  @Test
  public void MyPlanService_DeleteEntry_SuccessfullyDeletes() {
    myPlanService.deleteEntry("testID");
    Mockito.verify(myPlanEntryRepository).deleteMyPlanEntryById("testID");
  }

  @Test
  public void MyPlanService_GetProduction_ReturnsFilmProduction() {
    Mockito.when(restTemplate.getForObject(rootUrl + "movie/" + filmEntry.getProductionId() + "?api_key=" + apiKey, Movie.class)).thenReturn(new Movie());
    Production production = myPlanService.getProduction(filmEntry);
    Assertions.assertThat(production).isNotNull().isInstanceOf(Movie.class);
  }

  @Test
  public void MyPlanService_GetProduction_ReturnsTVProduction() {
    Mockito.when(restTemplate.getForObject(rootUrl + "tv/" + tvEntry.getProductionId() + "?api_key=" + apiKey, Series.class)).thenReturn(new Series());
    Production production = myPlanService.getProduction(tvEntry);
    Assertions.assertThat(production).isNotNull().isInstanceOf(Series.class);
  }

  @Test
  public void MyPlanService_GetEntriesOnDate_ReturnsEntries() {
    Mockito.when(myPlanEntryRepository.findMyPlanEntriesByUserIdAndDate(Mockito.any(String.class),Mockito.any(java.util.Date.class))).thenReturn(new ArrayList<>());
    Map<String, Production> entries = myPlanService.getEntriesOnDate("userID123",Date.valueOf("2023-10-27"));
    Assertions.assertThat(entries).isNotNull();
  }
}
