package com.sparta.projectmovie1.movienightplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Production {

    private int id;

//    @JsonProperty("title")

    protected String name;
    private String imagePath;
    protected List<Genre> genres;
    protected List<SpokenLanguage> spokenLanguages;



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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    @Override
    public String toString() {
        return "Production{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", genres=" + genres +
                ", spokenLanguages=" + spokenLanguages +
                '}';
    }
}
