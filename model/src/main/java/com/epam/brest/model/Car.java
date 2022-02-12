package com.epam.brest.model;

import java.math.BigDecimal;

public class Car {
    private Integer carId;
    private String model;
    private BigDecimal price;


    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }

    public Car(String model, BigDecimal price) {
        this.model = model;
        this.price = price;
    }

    public Car(Integer carId, String model, BigDecimal price) {
        this.carId = carId;
        this.model = model;
        this.price = price;
    }

    public Integer getCarId() {
        return carId;
    }

    public Car setCarId(Integer carId) {
        this.carId = carId;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Car setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }



}
