package com.sparta.projectmovie1.movienightplanner.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document("myPlanEntries")
public class MyPlanEntry {

  @Id
  private String id;

  @Field
  private int productionId;

  @Field
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date date;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getProductionId() {
    return productionId;
  }

  public void setProductionId(int productionId) {
    this.productionId = productionId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "MyPlanEntry{" +
        "id='" + id + '\'' +
        ", productionId='" + productionId + '\'' +
        ", date=" + date +
        '}';
  }
}
