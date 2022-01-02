package com.epam.brest.model;

public class Car {
    private Integer carId;
    private String model;


    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }

    public Car(Integer carId, String model) {
        this.carId = carId;
        this.model = model;
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

}
