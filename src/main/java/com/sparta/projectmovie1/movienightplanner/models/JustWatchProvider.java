package com.sparta.projectmovie1.movienightplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class JustWatchProvider {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("technical_name")
  private String technicalName;

  @JsonProperty("short_name")
  private String shortName;

  @JsonProperty("clear_name")
  private String clearName;

  @JsonProperty("monetization_types")
  private List<String> monetizationTypes;

  @JsonProperty("icon_url")
  private String iconUrl;

  @JsonProperty("slug")
  private String slug;

  public Integer getId() {
    return id;
  }

  public String getTechnicalName() {
    return technicalName;
  }

  public String getShortName() {
    return shortName;
  }

  public String getClearName() {
    return clearName;
  }

  public List<String> getMonetizationTypes() {
    return monetizationTypes;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public String getSlug() {
    return slug;
  }

}
