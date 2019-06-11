package com.kb.application.controller;

import com.kb.application.model.dao.IpEntity;
import com.kb.application.model.dao.WeatherEntity;
import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.model.rest.Weather;
import com.kb.application.service.GeolocationService;
import com.kb.application.service.WeatherService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {

    public static final String IP = "12345";

    private final GeolocationService geolocationService = mock(GeolocationService.class);
    private final WeatherService weatherService = mock(WeatherService.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);

    private final WeatherController weatherController = new WeatherController(geolocationService, weatherService);

    GeolocationResponse geolocationResponse = new GeolocationResponse("1","2");
    Weather weather = new Weather("main", "description");
    LocalDateTime now = LocalDateTime.now();
    List<IpEntity> ipEntities = Collections.singletonList(new IpEntity(1, "134", now));
    List<WeatherEntity> weatherEntities = Collections.singletonList(new WeatherEntity(1, "main", "descr", "55", "66", now));

    @Test
    public void getWeatherTest() {
        when(geolocationService.getGeolocation(IP)).thenReturn(geolocationResponse);
        when(weatherService.getWeather(geolocationResponse)).thenReturn(weather);
        when(request.getHeader("X-Forwarded-For")).thenReturn(IP);

        assertEquals(weather, weatherController.getCurrentWeather(request));

        verify(geolocationService, timeout(1)).getGeolocation(IP);
        verify(weatherService, timeout(1)).getWeather(geolocationResponse);
        verify(request, timeout(1)).getHeader("X-Forwarded-For");
    }

    @Test
    public void getIpHistoryTest() {
        when(geolocationService.getIpHistory()).thenReturn(ipEntities);

        assertEquals(ipEntities, weatherController.getIpHistory());

        verify(geolocationService, times(1)).getIpHistory();
    }

    @Test
    public void getWeatherHistoryTest() {
        when(weatherService.getWeatherHistory()).thenReturn(weatherEntities);

        assertEquals(weatherEntities, weatherController.getWeatherHistory());

        verify(weatherService, times(1)).getWeatherHistory();
    }
}
