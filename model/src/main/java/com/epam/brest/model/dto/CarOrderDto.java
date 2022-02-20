package com.epam.brest.model.dto;

import com.epam.brest.model.OrderStatus;

import java.util.Date;

public class CarOrderDto {

    private Integer orderId;
    private Date date;
    private String customer;
    private Integer carId;
    private OrderStatus orderStatus;
    private Integer rentalPeriod;

    public CarOrderDto() {
    }

    public CarOrderDto(Integer orderId, Date date, String customer, Integer carId, OrderStatus orderStatus, Integer rentalPeriod) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
        this.carId = carId;
        this.orderStatus = orderStatus;
        this.rentalPeriod = rentalPeriod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }
}
