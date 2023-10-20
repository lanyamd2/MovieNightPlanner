package com.sparta.projectmovie1.movienightplanner.models.movies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.SpokenLanguage;

public class Movie extends Production {

    @JsonProperty("crew")
    private List<Crew> crew;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;


    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getRuntime() {
        return runtime;
    }

    @Override
    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    @Override
    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
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
                        ",crew = '" + crew + '\'' +
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