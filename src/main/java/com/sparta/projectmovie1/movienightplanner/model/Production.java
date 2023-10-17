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
    @JsonAlias("genre_ids")
    protected List<Genre> genres;

   // @JsonProperty("genres")
    //@JsonAlias("genre_ids")
    private List<Integer> genre_ids;
    private String media_type;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Object getPopularity() {
        return popularity;
    }

    public void setPopularity(Object popularity) {
        this.popularity = popularity;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }
}
