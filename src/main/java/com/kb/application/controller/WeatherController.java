package com.kb.application.controller;

import com.kb.application.model.dao.IpEntity;
import com.kb.application.model.dao.WeatherEntity;
import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.model.rest.Weather;
import com.kb.application.service.GeolocationService;
import com.kb.application.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.kb.application.util.IpExtractor.getClientIpAddress;

@RestController
public class WeatherController {

    private GeolocationService geolocationService;

    private WeatherService weatherService;

    @Autowired
    public WeatherController(GeolocationService geolocationService, WeatherService weatherService) {
        this.geolocationService = geolocationService;
        this.weatherService = weatherService;
    }

    @RequestMapping("/weather")
    public Weather getCurrentWeather(HttpServletRequest request) {
        GeolocationResponse geolocationResponse = geolocationService.getGeolocation(getClientIpAddress(request));
        return weatherService.getWeather(geolocationResponse);
    }

    @RequestMapping("/ipHistory")
    public List<IpEntity> getIpHistory(){
        return geolocationService.getIpHistory();
    }

    @RequestMapping("/weatherHistory")
    public List<WeatherEntity> getWeatherHistory(){
        return weatherService.getWeatherHistory();
    }
}