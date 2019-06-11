package com.kb.application.service;

import com.kb.application.model.dao.IpEntity;
import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.repository.IpRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.kb.application.controller.WeatherControllerTest.IP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GeolocationServiceTest {

    private RestService restService = mock(RestService.class);
    private IpRepository ipRepository = mock(IpRepository.class);
    private GeolocationResponse geolocationResponse = new GeolocationResponse("1","2");

    private GeolocationService geolocationService = new GeolocationService(restService, ipRepository);
    private IpEntity ipEntity = new IpEntity(5,"1",LocalDateTime.now());

    @Test
    public void getGeolocationTest() {
        when(restService.getGeolocation(IP)).thenReturn(geolocationResponse);
        when(ipRepository.save(any())).thenReturn(ipEntity);

        geolocationService.getGeolocation(IP);
        ArgumentCaptor<IpEntity> ipEntityArgumentCaptor = ArgumentCaptor.forClass(IpEntity.class);

        verify(restService, times(1)).getGeolocation(IP);
        verify(ipRepository, times(1)).save(ipEntityArgumentCaptor.capture());

        IpEntity ipEntitySaved = ipEntityArgumentCaptor.getValue();
        assertEquals(IP, ipEntitySaved.getIp());
    }

    @Test
    public void getIpHistoryTest(){
        List<IpEntity> expectedIpEntities = Collections.singletonList(ipEntity);
        when(ipRepository.findAll()).thenReturn(expectedIpEntities);

        List<IpEntity> actualIpEntities = geolocationService.getIpHistory();

        assertEquals(expectedIpEntities.size(), actualIpEntities.size());
        assertEquals(expectedIpEntities.get(0), actualIpEntities.get(0));
    }
}
