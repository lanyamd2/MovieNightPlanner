package com.sparta.projectmovie1.movienightplanner.models;

public class LastSearchCriteria {

    private String searchQuery;
    private String productionType;
    private Integer searchGenre;

    public LastSearchCriteria(String searchQuery, String productionType, Integer searchGenre) {
        this.searchQuery = searchQuery;
        this.productionType = productionType;
        this.searchGenre = searchGenre;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public Integer getSearchGenre() {
        return searchGenre;
    }

    public void setSearchGenre(Integer searchGenre) {
        this.searchGenre = searchGenre;
    }
}
