package com.epam.brest.model.dto;

import java.time.Instant;

public class CarOrderDto {


    /**
     * Car order Id.
     */
    private Integer orderId;

    /**
     * Date order.
     */
    private Instant dateOrder;

    /**
     * Customer.
     */
    private String customer;

    /**
     * Car Id.
     */
    private Integer carId;

    /**
     * Rental period.
     */
    private Integer rentalPeriod;

    /**
     * Constructor without arguments.
     */
    public CarOrderDto() {
    }

    /**
     * Constructor with customer, car ID, rental period.
     *
     * @param  customer
     * @param carId
     * @param rentalPeriod
     */
    public CarOrderDto(String customer, Integer carId, Integer rentalPeriod) {
        this.customer = customer;
        this.carId = carId;
        this.rentalPeriod = rentalPeriod;
    }


    public CarOrderDto(Instant dareOrder, String customer, Integer carId, Integer rentalPeriod) {
        this.dateOrder = dareOrder;
        this.customer = customer;
        this.carId = carId;
        this.rentalPeriod = rentalPeriod;
    }

    public CarOrderDto(Integer orderId, Instant dareOrder, String customer, Integer carId, Integer rentalPeriod) {
        this.dateOrder = dareOrder;
        this.orderId = orderId;
        this.customer = customer;
        this.carId = carId;
        this.rentalPeriod = rentalPeriod;
    }

    /**
     * Returns <code>Integer</code> representation of this carOrderId.
     *
     * @return carOrderId Car Order Id.
     */
    public Integer getOrderId() {
        return orderId;
    }
    /**
     * Sets the car order's identifier.
     *
     * @param orderId Car Order Id.
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns <code>Instant</code> representation of this dateOrder.
     *
     * @return dateOrder Date Order.
     */
    public Instant getDateOrder() {
        return dateOrder;
    }

    /**
     * Sets the date car's order.
     *
     * @param dateOrder Date order.
     */
    public void setDateOrder(Instant dateOrder) {
        this.dateOrder = dateOrder;
    }

    /**
     * Returns <code>String</code> representation of this customer.
     *
     * @return customer Customer.
     */
    public String getCustomer() {
        return customer;
    }
    /**
     * Sets the customer's.
     *
     * @param customer Customer.
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Returns <code>Integer</code> representation of car ID.
     *
     * @return carId Car ID.
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * Sets the carId identifier.
     *
     * @param carId Car Id.
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     * Returns <code>Integer</code> representation of this rental period.
     *
     * @return rentalPeriod Rental Period.
     */
    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    /**
     * Sets the rental period.
     *
     * @param rentalPeriod Rental period.
     */
    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }
}
