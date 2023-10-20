package com.sparta.projectmovie1.movienightplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("myPlanEntries")
public class MyPlanEntry {

  @Id
  @JsonProperty("_id")
  private String id;

  @JsonProperty("production_id")
  private Integer productionId;

  @JsonProperty("is_movie")
  private boolean isMovie;

  @JsonProperty("date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;

  public boolean isMovie() {
    return isMovie;
  }

  public void setMovie(boolean movie) {
    isMovie = movie;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getProductionId() {
    return productionId;
  }

  public void setProductionId(Integer productionId) {
    this.productionId = productionId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public MyPlanEntry(String id, Integer productionId, boolean isMovie, Date date) {
    this.id = id;
    this.productionId = productionId;
    this.isMovie = isMovie;
    this.date = date;
  }

  public MyPlanEntry() {}

  @Override
  public String toString() {
    return "MyPlanEntry{" +
        "id='" + id + '\'' +
        ", productionId='" + productionId + '\'' +
        ", isMovie=" + isMovie +
        ", date=" + date +
        '}';
  }
}
