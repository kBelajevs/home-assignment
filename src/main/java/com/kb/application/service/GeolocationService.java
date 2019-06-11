package com.kb.application.service;

import com.kb.application.model.dao.IpEntity;
import com.kb.application.model.rest.GeolocationResponse;
import com.kb.application.repository.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GeolocationService {

    private RestService restService;
    private IpRepository ipRepository;

    @Autowired
    public GeolocationService(RestService restService, IpRepository ipRepository) {
        this.restService = restService;
        this.ipRepository = ipRepository;
    }

    public GeolocationResponse getGeolocation(String ipAddress) {
        CompletableFuture.supplyAsync(() -> ipRepository.save(new IpEntity(ipAddress, LocalDateTime.now())));
        return restService.getGeolocation(ipAddress);
    }

    public List<IpEntity> getIpHistory(){
        List<IpEntity> ipHistory = new ArrayList<>();
        ipRepository.findAll().forEach(ipHistory::add);
        return ipHistory;
    }
}
