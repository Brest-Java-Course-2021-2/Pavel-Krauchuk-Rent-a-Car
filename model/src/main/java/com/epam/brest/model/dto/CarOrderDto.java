package com.epam.brest.model.dto;

import com.epam.brest.model.Car;
import com.epam.brest.model.Customer;
import com.epam.brest.model.OrderStatus;

import java.util.Date;

public class CarOrderDto {

    private Integer orderCarId;
    private Date date;
    private Customer customer;
    private Car car;
    private OrderStatus orderStatus;

    public CarOrderDto(Date date, Customer customer, Car car, OrderStatus orderStatus) {
        this.date = date;
        this.customer = customer;
        this.car = car;
        this.orderStatus = orderStatus;
    }

    public Integer getOrderCarId() {
        return orderCarId;
    }

    public void setOrderCarId(Integer orderCarId) {
        this.orderCarId = orderCarId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "CarOrderDto{" +
                "orderCarId=" + orderCarId +
                ", date=" + date +
                ", customer=" + customer +
                ", car=" + car +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
