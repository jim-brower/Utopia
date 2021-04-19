package com.ss.entity;

public class Route {
    private Integer id;
    private Airport origin = new Airport();
    private Airport destination = new Airport();

    public Route(Airport origin, Airport destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Route() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport oriAirport) {
        this.origin = oriAirport;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport desAirport) {
        this.destination = desAirport;
    }
}
