package com.epam.brest.model.dto;

import java.time.Instant;

public class CarOrderDto {

    private Integer orderId;
    private Instant dateOrder;
    private String customer;
    private Integer carId;
    private Integer rentalPeriod;

    public CarOrderDto() {
    }

    public CarOrderDto(Instant dateOrder, String customer, Integer carId, Integer rentalPeriod) {
        this.dateOrder = dateOrder;
        this.customer = customer;
        this.carId = carId;
        this.rentalPeriod = rentalPeriod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Instant getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Instant dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }
}
