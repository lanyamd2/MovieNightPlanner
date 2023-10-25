package com.sparta.projectmovie1.movienightplanner.services;

import com.sparta.projectmovie1.movienightplanner.models.movies.Movie;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.ProductionNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieServiceTests {

    @Value("${tmdb.api.key}")
    static String tmdbApiKey;

    MovieService movieService;

    WebTestClient webTestClient;

    @Autowired
    public MovieServiceTests(MovieService movieService, WebTestClient webTestClient) {
        this.movieService = movieService;
        this.webTestClient=webTestClient;
    }


    @Test
    public void shouldReturnMovieGivenId(){
        Assertions.assertEquals(2108,movieService.getMovieById("2108").getId());
    }

    @Test
    public void shouldThrowProductionNotFoundExceptionIfMovieIsNotFound(){
        Assertions.assertThrows(ProductionNotFoundException.class, ()->movieService.getMovieById("123456"));
    }

    @Test
    public void shouldReturnReleaseYearFromGivenReleaseDate(){
        String url = "https://api.themoviedb.org/3/movie/2108?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        Movie movie = this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                        .returnResult(Movie.class).getResponseBody().blockFirst();

        Assertions.assertEquals("1985", movieService.getReleaseYearFromReleaseDate(movie));
    }


    @Test
    public void shouldReturnListOfOffersWhenFoundOnJustWatchApi(){
        String url = "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/movie/locale/en_GB?title=the breakfast club&release_year=1985&token=ABCdef12";
        this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.title").isEqualTo("The Breakfast Club")
                .jsonPath("$.original_release_year").isEqualTo("1985");

    }

    @Test
    public void shouldReturnServerErrorStatusWhenMovieNotFoundOnJustWatchApi(){
        String url = "https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/movie/locale/en_GB?title=hello&release_year=1985&token=ABCdef12";
        this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .returnResult(Void.class);
    }


    @ParameterizedTest
    @MethodSource("justWatchUrlTestCases")
    public void shouldReturnJustWatchUrl(String type, String locale, String title, String releaseYear, String expected){
        Assertions.assertEquals(expected, movieService.getJustWatchUrl(title,type,releaseYear,locale));
    }
    private static Stream<Arguments> justWatchUrlTestCases(){
        return Stream.of(
                Arguments.arguments("movie","en_GB","the breakfast club","1985","https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/movie/locale/en_GB?title=the breakfast club &release_year=1985&token=ABCdef12"),
                Arguments.arguments("show","en_GB", "wonder woman","1976","https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/show/locale/en_GB?title=wonder woman &release_year=1976&token=ABCdef12")
        );
    }

    @Test
    public void shouldReturnCreditsWhenMovieIdIsFoundOnTmdb(){
        String url = "https://api.themoviedb.org/3/movie/2108/credits?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        this.webTestClient
                .get()
                .uri(url)
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo("2108");
    }
    @Test
    public void shouldReturnMovieWhenMovieIdIsFoundOnTmdb(){
        String url = "https://api.themoviedb.org/3/movie/2108?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        this.webTestClient
                .get()
                .uri(url)
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.title").isEqualTo("The Breakfast Club")
                .jsonPath("$.id").isEqualTo("2108");
    }

    @Test
    public void shouldReturnNotFoundStatusWhenIdIsNotFoundOnTmdbApi(){
        String url = "https://api.themoviedb.org/3/movie/123456?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4";
        this.webTestClient
                .get()
                .uri(url)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND)
                .returnResult(Void.class);
    }

    @ParameterizedTest
    @MethodSource("tmdbUrlTestCases")
    public void shouldReturnTmdbUrlString(String id, String type, String expected){
        Assertions.assertEquals(expected, movieService.getTmdbUrl(id, type));
    }
    private static Stream<Arguments> tmdbUrlTestCases(){
        return Stream.of(
          Arguments.arguments("123456","movie","https://api.themoviedb.org/3/movie/123456?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4"),
          Arguments.arguments("123456","tv","https://api.themoviedb.org/3/tv/123456?api_key=923f97b514ce2aa53b0bfdb2ad5af5d4")
        );
    }
}
