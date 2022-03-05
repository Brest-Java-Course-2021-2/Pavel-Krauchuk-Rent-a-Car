package com.epam.brest.model;

import java.math.BigDecimal;

public class Car {

    /**
     * Car Id.
     */
    private Integer carId;

    /**
     * Car model.
     */
    private String model;

    /**
     * Price.
     */
    private BigDecimal price;

    /**
     * Constructor without arguments.
     */
    public Car() {
    }

    /**
     * Constructor with car model.
     *
     * @param model car model
     */
    public Car(String model) {
        this.model = model;
    }

    public Car(String model, BigDecimal price) {
        this.model = model;
        this.price = price;
    }

    public Car(Integer carId, String model, BigDecimal price) {
        this.carId = carId;
        this.model = model;
        this.price = price;
    }

    /**
     * Returns <code>Integer</code> representation of this carId.
     *
     * @return carId Car Id.
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * Sets the car's identifier.
     *
     * @param carId Car Id.
     */
    public Car setCarId(Integer carId) {
        this.carId = carId;
        return this;
    }

    /**
     * Returns <code>String</code> representation of this model.
     *
     * @return model Car model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the Car's model.
     *
     * @param model Car model.
     */
    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * Returns <code>BigDecimal</code> representation of price.
     *
     * @return price Car price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the car's price.
     *
     * @param price Car price.
     */
    public Car setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
