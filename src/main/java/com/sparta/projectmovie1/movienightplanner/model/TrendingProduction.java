package com.sparta.projectmovie1.movienightplanner.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrendingProduction {

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
    //protected List<Genre> genres;
    protected List<Integer> genres;

    @JsonProperty("original_language")
    protected String originalLanguage;

    @JsonProperty("popularity")
    protected Object popularity;

    @JsonProperty("vote_average")
    protected Object voteAverage;

    @JsonProperty("vote_count")
    protected int voteCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Object getPopularity() {
        return popularity;
    }

    public void setPopularity(Object popularity) {
        this.popularity = popularity;
    }

    public Object getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Object voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
