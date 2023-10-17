package com.sparta.projectmovie1.movienightplanner.models.movies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.SpokenLanguage;

public class Movie extends Production {
    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

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
                        ",release_date = '" + super.releaseDate + '\'' +
                        ",genres = '" + super.genres + '\'' +
                        ",popularity = '" + super.popularity + '\'' +
                        ",vote_average = '" + super.voteAverage + '\'' +
                        ",production_countries = '" + super.productionCountries + '\'' +
                        ",tagline = '" + super.tagline + '\'' +
                        ",vote_count = '" + super.voteCount + '\'' +
                        ",status = '" + super.status + '\'' +
                        "}";
    }
}