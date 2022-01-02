package com.epam.brest.model;

import java.math.BigDecimal;
import java.util.Date;

public class CarOrder {

    private Integer orderId;
    private Date date;
    private Integer customerId;
    private Integer carId;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;

    public CarOrder() {
    }

    public CarOrder(Integer orderId, Date date, Integer customerId, Integer carId, OrderStatus orderStatus, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.carId = carId;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
