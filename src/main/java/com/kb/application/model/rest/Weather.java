package com.kb.application.model.rest;

public class Weather {

    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public Weather(){}

    public String main;
    public String description;
}
