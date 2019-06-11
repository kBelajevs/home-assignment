package com.kb.application.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue
    private long id;
    private String main;
    private String description;
    private String latitude;
    private String longitude;
    private LocalDateTime createDateTime;

    public WeatherEntity() {
    }

    public WeatherEntity(String main, String description, String latitude, String longitude, LocalDateTime createDateTime) {
        this.main = main;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createDateTime = createDateTime;
    }

    public WeatherEntity(long id, String main, String description, String latitude, String longitude, LocalDateTime createDateTime) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createDateTime = createDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
