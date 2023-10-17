package com.sparta.projectmovie1.movienightplanner.model;

import java.util.List;

public class ProductionList {

    Integer page;

    List<Production> results;

    Integer total_pages;

    public ProductionList() {
    }

    public ProductionList(Integer page, List<Production> results, Integer total_pages) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Production> getResults() {
        return results;
    }

    public void setResults(List<Production> results) {
        this.results = results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }
}
