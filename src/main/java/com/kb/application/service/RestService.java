package com.kb.application.service;

import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.model.rest.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.kb.application.config.Config.GEOLOCATION_CACHE;
import static com.kb.application.config.Config.WEATHER_CACHE;

@Component
public class RestService {

    private static final String GEOLOCATION_URL = "https://api.ipgeolocation.io/ipgeo?apiKey=52144b155380421880def44dcf7f8d55&ip=%s";
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&APPID=98475faa2a8e6880fb74ae7131398bb3&units=metric";

    private RestTemplate restTemplate;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

   @Cacheable(value = GEOLOCATION_CACHE)
   public GeolocationResponse getGeolocation(String ipAddress) {
        try {
            return restTemplate.getForObject(String.format(GEOLOCATION_URL, ipAddress), GeolocationResponse.class);
        } catch (Exception e){
            throw new RuntimeException("Geolocation Service is unavailable, try later");
        }
    }

    @Cacheable(value = WEATHER_CACHE)
   public WeatherResponse getWeather(String latitude, String longitude) {
        try {
            return restTemplate.getForObject(String.format(WEATHER_URL, latitude, longitude), WeatherResponse.class);
        } catch (Exception e){
            throw new RuntimeException("Weather Forecast Service is unavailable, try later");
        }
    }
}
