package com.sparta.projectmovie1.movienightplanner.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.format.annotation.DateTimeFormat;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Production {
    protected Integer id;

    @JsonProperty("title")
    @JsonAlias("name")
    protected String name;//should this be name or title when writing to JSON

    @JsonProperty("overview")
    protected String overview;

    @JsonProperty("release_date")
    @JsonAlias("first_air_date")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected String releaseDate;

    protected Integer releaseYear;

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
    protected Integer voteCount;

    @JsonProperty("status")
    protected String status;

    @JsonProperty("tagline")
    protected String tagline;

    @JsonProperty("production_countries")
    protected List<ProductionCountry> productionCountries;

    @JsonProperty("offers")
    protected List<Offer> offers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Production() {
    }

    public Production(Integer id, String name, String overview, String releaseDate, String originalTitle, String posterPath, List<Genre> genres, List<Integer> genre_ids, String media_type, List<SpokenLanguage> spokenLanguages, String originalLanguage, Object popularity, Object voteAverage, Integer voteCount, String status, String tagline, List<ProductionCountry> productionCountries, List<Offer> offers) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.genres = genres;
        this.genre_ids = genre_ids;
        this.media_type = media_type;
        this.spokenLanguages = spokenLanguages;
        this.originalLanguage = originalLanguage;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.status = status;
        this.tagline = tagline;
        this.productionCountries = productionCountries;
        this.offers = offers;
    }
}
