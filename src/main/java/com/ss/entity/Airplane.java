package com.ss.entity;

public class Airplane {
    private Integer id;
    private AirplaneType typeId = new AirplaneType();

    public Airplane(Integer id, AirplaneType typeId) {
        this.id = id;
        this.typeId = typeId;
    }

    public Airplane() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public AirplaneType getTypeId() {
        return typeId;
    }
    public void setTypeId(AirplaneType typeId) {
        this.typeId = typeId;
    }
}
