package com.ss.entity;

public class Airport {
    private String airportCode;
    private String cityName;

    public Airport(String code, String name) {
        this.airportCode = code;
        this.cityName = name;
    }

    public Airport() {

    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
