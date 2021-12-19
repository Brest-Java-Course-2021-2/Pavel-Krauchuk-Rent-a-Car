package com.epam.brest.model;

import java.math.BigDecimal;

public class Car {
    private Integer carId;
    private String model;
    private String color;
    private BigDecimal price;

    public Car(String model) {
        this.model = model;
    }

    public Car(Integer carId, String model, String color, BigDecimal price) {
        this.carId = carId;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public Car(Integer carId, String model, String color) {
        this.carId = carId;
        this.model = model;
        this.color = color;
    }

    public Car() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
