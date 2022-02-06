package com.epam.brest.model;

import java.math.BigDecimal;

public class Car {
    private Integer carId;
    private String model;
    private BigDecimal price;


    public Car() {
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

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



}
