package com.sparta.projectmovie1.movienightplanner.model.movies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.model.Production;
import com.sparta.projectmovie1.movienightplanner.model.SpokenLanguage;

public class Movie extends Production {
    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountries;


    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    @Override
    public String toString() {
        return
                "Movie{" +
                        ",overview = '" + super.overview + '\'' +
                        ",original_language = '" + super.originalLanguage + '\'' +
                        ",original_title = '" + super.originalTitle + '\'' +
                        ",imdb_id = '" + imdbId + '\'' +
                        ",runtime = '" + runtime + '\'' +
                        ",title = '" + super.name + '\'' +
                        ",poster_path = '" + super.posterPath + '\'' +
                        ",spoken_languages = '" + super.spokenLanguages + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",genres = '" + super.genres + '\'' +
                        ",popularity = '" + super.popularity + '\'' +
                        ",vote_average = '" + super.voteAverage + '\'' +
                        ",production_countries = '" + productionCountries + '\'' +
                        ",tagline = '" + super.tagline + '\'' +
                        ",vote_count = '" + super.voteCount + '\'' +
                        ",status = '" + super.status + '\'' +
                        "}";
    }
}