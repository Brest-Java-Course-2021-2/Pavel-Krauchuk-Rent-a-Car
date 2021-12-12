package com.epam.brest.model.dto;

import java.math.BigDecimal;

public class CarDto {

    private Integer carId;
    private String model;
    private String color;
    private Integer yearOfIssue;
    private String carNumber;
    private BigDecimal price;

    public CarDto() {
    }

    public CarDto(String model, String color, Integer yearOfIssue, String carNumber, BigDecimal price) {
        this.model = model;
        this.color = color;
        this.yearOfIssue = yearOfIssue;
        this.carNumber = carNumber;
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

    @Override
    public String toString(){
        return "CarDto{" + "carId=" + carId + '\'' + "model=" + model + '\''
                + "color=" + color + '\'' + "yearOfIssue=" + yearOfIssue + '\''
                + "carNumber=" + carNumber + '\'' + "price" + price + '}';
    }
}
