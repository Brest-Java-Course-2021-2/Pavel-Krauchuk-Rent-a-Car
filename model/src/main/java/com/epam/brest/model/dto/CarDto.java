package com.epam.brest.model.dto;

/**
 * POJO Car for model.
 */
import java.math.BigDecimal;

public class CarDto {

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
    public CarDto() {
    }

    /**
     * Constructor with car model.
     *
     * @param model car model
     */
    public CarDto(String model) {
        this.model = model;
    }

    public CarDto(String model, BigDecimal price) {
        this.model = model;
        this.price = price;
    }

    public CarDto(Integer carId, String model, BigDecimal price) {
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
    public CarDto setCarId(Integer carId) {
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
    public CarDto setModel(String model) {
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
    public CarDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CarDto{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}

