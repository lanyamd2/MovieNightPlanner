package com.sparta.projectmovie1.movienightplanner.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTests {
    WebTestClient webTestClient;

    @Autowired
    public ProfileControllerTests(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    public void shouldReturnNotFoundStatusIfMovieNotFoundById(){
        this.webTestClient
                .get()
                .uri("/api/details/movie/{id}",0)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(NOT_FOUND);
    }
    @Test
    public void shouldReturnMovieDetailsIfMovieIsFoundById(){
        this.webTestClient
                .get()
                .uri("/api/details/movie/{id}",2108)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("The Breakfast Club");
    }

    @Test
    public void shouldReturnNotFoundStatusIfSeriesNotFoundById(){
        this.webTestClient
                .get()
                .uri("/api/details/tv/{id}",0)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(NOT_FOUND);
    }
    @Test
    public void shouldReturnSeriesDetailsIfSeriesIsFoundById(){
        this.webTestClient
                .get()
                .uri("/api/details/tv/{id}",1396)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("Breaking Bad");
    }

    @Test
    public void shouldReturnOKStatusIfMovieNotFoundOnJustWatch(){
        this.webTestClient
                .get()
                .uri("/api/details/justwatch/no movie/movie/2008")
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(OK)
                .expectBody()
                .jsonPath("$").isEmpty();

    }

    @Test
    public void shouldReturnMovieOffersIfMovieIsFoundOnJustWatch(){
        this.webTestClient
                .get()
                .uri("/api/details/justwatch/the breakfast club/movie/1985")
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].currency").isEqualTo("GBP");
    }
    @Test
    public void shouldReturnNotFoundStatusIfShowNotFoundOnJustWatch(){
        this.webTestClient
                .get()
                .uri("/api/details/justwatch/no show/show/2008")
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldReturnShowOffersIfSeriesIsFoundOnJustWatch(){
        this.webTestClient
                .get()
                .uri("/api/details/justwatch/breaking bad/show/2008")
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].currency").isEqualTo("GBP");
    }

    @Test
    public void shouldReturnNotFoundStatusIfMovieNotFoundByIdOnTmdb(){
        this.webTestClient
                .get()
                .uri("/api/details/movie/tmdb/{id}",0)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(NOT_FOUND);
    }
    @Test
    public void shouldReturnMovieDetailsIfMovieIsFoundByIdOnTmdb(){
        this.webTestClient
                .get()
                .uri("/api/details/movie/tmdb/{id}",2108)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("The Breakfast Club");
    }

    @Test
    public void shouldReturnNotFoundStatusIfSeriesNotFoundByIdOnTmdb(){
        this.webTestClient
                .get()
                .uri("/api/details/tv/tmdb/{id}",0)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(NOT_FOUND);
    }
    @Test
    public void shouldReturnSeriesDetailsIfSeriesIsFoundOnTmdb(){
        this.webTestClient
                .get()
                .uri("/api/details/tv/tmdb/{id}",1396)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("Breaking Bad");
    }

    @Test
    public void shouldReturnNotFoundStatusIfDirectorsNotFoundById(){
        this.webTestClient
                .get()
                .uri("/api/details/movie/directors/{id}",0)
                .header(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldReturnDirectorsIfMovieIsFoundOnTmdb(){
        this.webTestClient
                .get()
                .uri("/api/details/movie/directors/{id}",2108)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("John Hughes");
    }
}
