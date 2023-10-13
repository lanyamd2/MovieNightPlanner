package com.sparta.projectmovie1.movienightplanner.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Production {
    protected int id;

    @JsonProperty("title")
    @JsonAlias("name")
    protected String name;//should this be name or title when writing to JSON

    @JsonProperty("overview")
    protected String overview;

    @JsonProperty("original_title")
    @JsonAlias("original_name")
    protected String originalTitle;

    @JsonProperty("poster_path")
    protected String posterPath;

    @JsonProperty("genres")
    protected List<Genre> genres;

    @JsonProperty("spoken_languages")
    protected List<SpokenLanguage> spokenLanguages;

    @JsonProperty("original_language")
    protected String originalLanguage;

    @JsonProperty("popularity")
    protected Object popularity;

    @JsonProperty("vote_average")
    protected Object voteAverage;

    @JsonProperty("vote_count")
    protected int voteCount;

    @JsonProperty("status")
    protected String status;

    @JsonProperty("tagline")
    protected String tagline;
}
