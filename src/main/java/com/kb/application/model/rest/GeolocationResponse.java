package com.kb.application.model.rest;


public class GeolocationResponse {

    public GeolocationResponse(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeolocationResponse(){}

    public String latitude;
    public String longitude;
}
