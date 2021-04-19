package com.ss.entity;

public class AirplaneType {
    private Integer id;
    private Integer maxCapacity;

    public AirplaneType(Integer id, Integer maxCapacity) {
        this.id = id;
        this.maxCapacity = maxCapacity;
    }

    public AirplaneType() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
