package com.epam.rest.model.dto;

import java.math.BigDecimal;

public class CarDto {

    private Integer carId;
    private String model;
    private String color;
    private BigDecimal price;

    public CarDto() {
    }

    public CarDto(String model, String color, BigDecimal price) {
        this.model = model;
        this.color = color;
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
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}

