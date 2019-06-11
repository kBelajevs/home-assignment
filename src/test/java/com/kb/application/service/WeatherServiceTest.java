package com.kb.application.service;

import com.kb.application.model.dao.IpEntity;
import com.kb.application.model.dao.WeatherEntity;
import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.model.rest.Weather;
import com.kb.application.model.rest.WeatherResponse;
import com.kb.application.repository.WeatherRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {


    private RestService restService = mock(RestService.class);
    private WeatherRepository weatherRepository = mock(WeatherRepository.class);
    private WeatherService weatherService = new WeatherService(restService, weatherRepository);
    private WeatherResponse weatherResponse = new WeatherResponse();
    private List<Weather> weathers = Collections.singletonList(new Weather("main", "description"));
    private GeolocationResponse geolocationResponse = new GeolocationResponse("1", "2");
    private WeatherEntity weatherEntityToSave = new WeatherEntity(1L, "main", "description", "1", "2", LocalDateTime.now());

    @Test
    public void getWeatherTest() {
        weatherResponse.weather = weathers;
        when(restService.getWeather(geolocationResponse.latitude, geolocationResponse.longitude)).thenReturn(weatherResponse);
        when(weatherRepository.save(any())).thenReturn(weatherEntityToSave);

        Weather actualWeather = weatherService.getWeather(geolocationResponse);
        assertEquals(weathers.get(0).description, actualWeather.description);
        assertEquals(weathers.get(0).main, actualWeather.main);

        ArgumentCaptor<WeatherEntity> weatherEntityArgumentCaptor = ArgumentCaptor.forClass(WeatherEntity.class);

        verify(restService, times(1)).getWeather("1", "2");
        verify(weatherRepository, times(1)).save(weatherEntityArgumentCaptor.capture());

        WeatherEntity weatherEntitySaved = weatherEntityArgumentCaptor.getValue();

        assertEquals(weatherEntityToSave.getDescription(), weatherEntitySaved.getDescription());
        assertEquals(weatherEntityToSave.getMain(), weatherEntitySaved.getMain());
        assertEquals(weatherEntityToSave.getLatitude(), weatherEntitySaved.getLatitude());
        assertEquals(weatherEntityToSave.getLongitude(), weatherEntitySaved.getLongitude());
    }

    @Test(expected = RuntimeException.class)
    public void getWeatherReturendEmptyListTest(){
        weatherResponse.weather = Collections.emptyList();
        when(restService.getWeather(geolocationResponse.latitude, geolocationResponse.longitude)).thenReturn(weatherResponse);
        weatherService.getWeather(geolocationResponse);
    }

    @Test(expected = RuntimeException.class)
    public void getWeatherReturendNullList(){
        weatherResponse.weather = null;
        when(restService.getWeather(geolocationResponse.latitude, geolocationResponse.longitude)).thenReturn(weatherResponse);
        weatherService.getWeather(geolocationResponse);
    }

    @Test
    public void getWeatherHistoryTest(){
        List<WeatherEntity> expectedEntities = Collections.singletonList(weatherEntityToSave);
        when(weatherRepository.findAll()).thenReturn(expectedEntities);

        List<WeatherEntity> actualEntities = weatherService.getWeatherHistory();

        assertEquals(expectedEntities.size(), actualEntities.size());
        assertEquals(expectedEntities.get(0), actualEntities.get(0));
    }
}
