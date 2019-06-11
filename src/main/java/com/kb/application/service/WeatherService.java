package com.kb.application.service;

import com.kb.application.model.dao.WeatherEntity;
import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.model.rest.Weather;
import com.kb.application.model.rest.WeatherResponse;
import com.kb.application.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {

    private RestService restService;
    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(RestService restService, WeatherRepository weatherRepository) {
        this.restService = restService;
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeather(GeolocationResponse geolocation) {
        WeatherResponse weatherResponse = restService.getWeather(geolocation.latitude, geolocation.longitude);
        if (weatherResponse.weather == null) {
            throw new RuntimeException("Could not find weather for given coordinates");
        }
        Weather weather = weatherResponse.weather.stream().findAny().orElseThrow(() -> new RuntimeException("Could not find weather for given coordinates"));
        CompletableFuture.supplyAsync(() -> weatherRepository.save(new WeatherEntity(weather.main, weather.description, geolocation.latitude, geolocation.longitude, LocalDateTime.now())));
        return weather;
    }

    public List<WeatherEntity> getWeatherHistory() {
        List<WeatherEntity> weatherHistory = new ArrayList<>();
        weatherRepository.findAll().forEach(weatherHistory::add);
        return weatherHistory;
    }
}
