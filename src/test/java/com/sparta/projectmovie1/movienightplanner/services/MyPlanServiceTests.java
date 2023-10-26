package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.MyPlanEntry;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.repositories.MyPlanEntryRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

  /*
  Check that an entry can be successfully saved

  Check that a production can be retrieved from an entry

  Check that a list of productions can be retrieved from a list of entries - cannot unit test. needs to be integration tested

  Check that a list of productions can be retrieved on a certain date

  Check that a map of all dates with productions can be returned - may need integration testing
   */

  @Test
  public void MyPlanService_AddEntry_SuccessfullySaves() {
    Mockito.when(myPlanEntryRepository.save(Mockito.any(MyPlanEntry.class))).thenReturn(filmEntry);
    MyPlanEntry savedEntry = myPlanService.addEntry(filmEntry);
    Assertions.assertThat(savedEntry).isNotNull().isEqualTo(filmEntry);
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
  public void MyPlanService_GetProductionsOnDate_ReturnsProductions() {
    Mockito.when(myPlanEntryRepository.findMyPlanEntriesByDate(Mockito.any(java.util.Date.class))).thenReturn(new ArrayList<>());
    List<Production> productions = myPlanService.getProductionsOnDate(Date.valueOf("2023-10-27"));
    Assertions.assertThat(productions).isNotNull();
  }
}
