package com.epam.brest.model;

import java.math.BigDecimal;

public class Car {
    private Integer carId;
    private String model;
    private String color;
    private Integer yearOfIssue;
    private String carNumber;
    private BigDecimal price;

    public Car(String model) {
        this.model = model;
    }

    public Car(Integer carId, String model, String color, BigDecimal price, Integer yearOfIssue, String carNumber) {
        this.carId = carId;
        this.model = model;
        this.color = color;
        this.price = price;
        this.yearOfIssue = yearOfIssue;
        this.carNumber = carNumber;
    }

    public Car(Integer carId, String model, String color, Integer yearOfIssue, String carNumber) {
        this.carId = carId;
        this.model = model;
        this.color = color;
        this.yearOfIssue = yearOfIssue;
        this.carNumber = carNumber;
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

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
