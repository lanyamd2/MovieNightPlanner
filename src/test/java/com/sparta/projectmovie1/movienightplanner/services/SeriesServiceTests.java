package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.ProductionNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeriesServiceTests {

    WebTestClient webTestClient;
    SeriesService seriesService;

    @Autowired
    public SeriesServiceTests(WebTestClient webTestClient, SeriesService seriesService) {
        this.webTestClient = webTestClient;
        this.seriesService = seriesService;
    }

    @Test
    public void shouldReturnMovieGivenId(){
        Assertions.assertEquals(1396,seriesService.getSeriesById("1396").getId());
    }

    @Test
    public void shouldThrowProductionNotFoundExceptionIfSeriesIsNotFound(){
        Assertions.assertThrows(ProductionNotFoundException.class, ()->seriesService.getSeriesById("0"));
    }

    @Test
    public void shouldReturnListOfOffersWhenFoundOnJustWatchApi(){
        String url = "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/show/locale/en_GB?title=breaking bad&release_year=2008&token=ABCdef12";
        this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Breaking Bad")
                .jsonPath("$.original_release_year").isEqualTo("2008");

    }

    @Test
    public void shouldReturnServerErrorStatusWhenShowIsNotFoundOnJustWatchApi(){
        String url = "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/show/locale/en_GB?title=hello&release_year=1985&token=ABCdef12";
        this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .returnResult(Void.class);
    }

    @Test
    public void shouldReturnCreditsWhenSeriesIdIsFoundOnTmdb(){
        String url = "https://api.themoviedb.org/3/tv/1396/credits?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        this.webTestClient
                .get()
                .uri(url)
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1396");
    }

    @Test
    public void shouldReturnMonoSeriesWhenSeriesIdIsFoundOnTmdb(){
        String url = "https://api.themoviedb.org/3/tv/1396?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        this.webTestClient
                .get()
                .uri(url)
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Breaking Bad")
                .jsonPath("$.id").isEqualTo("1396");

    }

    @Test
    public void shouldReturnNotFoundStatusWhenIdIsNotFoundOnTmdbApi(){
        String url = "https://api.themoviedb.org/3/tv/0?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND)
                .returnResult(Void.class);
    }
}
