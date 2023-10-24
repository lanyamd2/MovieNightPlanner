package com.sparta.projectmovie1.movienightplanner.services;


import com.sparta.projectmovie1.movienightplanner.models.tvshows.Series;
import com.sparta.projectmovie1.movienightplanner.services.exceptions.ProductionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.Optional;

@Service
public class SeriesService {
    private final WebClient webClient;
    private final MovieService movieService;

    @Autowired
    public SeriesService(WebClient webClient, MovieService movieService) {
        this.webClient = webClient;
        this.movieService = movieService;
    }

    public Series getSeriesById(String id) {
        Optional<Series> tmdbSeries = fetchTmdbSeriesById(id, "tv").blockOptional();
        if (tmdbSeries.isEmpty()) throw new ProductionNotFoundException("TV Show not found");
        tmdbSeries.get().setMedia_type("show");

        String title = tmdbSeries.get().getName().toLowerCase();

        int releaseYear = movieService.setProductionOffers(tmdbSeries.get(), title);
        tmdbSeries.get().setReleaseYear(releaseYear);

        return tmdbSeries.get();
    }

    public Mono<Series> fetchTmdbSeriesById(String id, String type) {
        String url = movieService.getTmdbUrl(id, type);
        return webClient.get()
                .uri(url)
                .exchangeToMono(this::handleSeriesResponse);
    }

    private Mono<Series> handleSeriesResponse(ClientResponse response) {
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(Series.class);
        } else if (response.statusCode().is4xxClientError()) {
            return Mono.error(new ProductionNotFoundException("TV Show not found"));
        } else if (response.statusCode().is5xxServerError()) {
            return Mono.error(new RuntimeException("Server error"));
        } else {
            return Mono.error(new RuntimeException("Unexpected error"));
        }
    }

}
