package com.kb.application.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponse {

    @JsonProperty("weather")
    public List<Weather> weather;
}
