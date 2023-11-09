package com.sparta.projectmovie1.movienightplanner.accounthistory;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document("watchHistoryEntries")
public class HistoryEntry {
    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("production_id")
    private Integer productionId;

    @JsonProperty("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    @JsonProperty("media_Type")
    private String mediaType;

    public HistoryEntry() {
    }

    public HistoryEntry(String userId, Integer productionId, Date date) {
        this.userId = userId;
        this.productionId = productionId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "HistoryEntry{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", productionId=" + productionId +
                ", date=" + date +
                ", mediaType=" + mediaType +
                '}';
    }
}
