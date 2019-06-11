package com.kb.application.service;

import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.model.rest.WeatherResponse;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static com.kb.application.controller.WeatherControllerTest.IP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestServiceTest {

    private static final String GEOLOCATION_URL = "https://api.ipgeolocation.io/ipgeo?apiKey=52144b155380421880def44dcf7f8d55&ip=12345";
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?lat=1&lon=2&APPID=98475faa2a8e6880fb74ae7131398bb3&units=metric";


    private RestTemplate restTemplate = mock(RestTemplate.class);
    private GeolocationResponse expectedGeolocationResponse = new GeolocationResponse("1", "2");
    private WeatherResponse expectedWeatherResponse = new WeatherResponse();
    private RestService restService = new RestService(restTemplate);

    @Test
    public void getGeolocationTest() {
        when(restTemplate.getForObject(GEOLOCATION_URL, GeolocationResponse.class)).thenReturn(expectedGeolocationResponse);

        GeolocationResponse actualGeolocationResponse = restService.getGeolocation(IP);

        assertEquals(expectedGeolocationResponse, actualGeolocationResponse);
    }

    @Test
    public void getGeolocationExceptionTest() {
        when(restTemplate.getForObject(GEOLOCATION_URL, GeolocationResponse.class)).thenThrow(new RuntimeException());
        try {
            restService.getGeolocation(IP);
        } catch (Exception e) {
            assertEquals("Geolocation Service is unavailable, try later", e.getMessage());
        }
    }

    @Test
    public void getWeatherTest() {
        when(restTemplate.getForObject(WEATHER_URL, WeatherResponse.class)).thenReturn(expectedWeatherResponse);

        WeatherResponse actualWeatherResponse = restService.getWeather("1","2");

        assertEquals(expectedWeatherResponse, actualWeatherResponse);
    }

    @Test
    public void getWeatherExceptionTest() {
        when(restTemplate.getForObject(WEATHER_URL, WeatherResponse.class)).thenThrow(new RuntimeException());
        try {
            restService.getWeather("1","2");
        } catch (Exception e) {
            assertEquals("Weather Forecast Service is unavailable, try later", e.getMessage());
        }
    }


}
