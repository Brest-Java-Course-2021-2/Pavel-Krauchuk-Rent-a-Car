package com.epam.brest.model.dto;

import java.math.BigDecimal;

public class CarDto {

    private Integer carId;
    private String model;
    private BigDecimal price;

    public CarDto() {
    }

    public CarDto(String model) {
        this.model = model;
    }

    public CarDto(String model, BigDecimal price) {
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

    @Override
    public String toString() {
        return "CarDto{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}

