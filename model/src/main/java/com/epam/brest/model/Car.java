package com.epam.brest.model;

public class Car {
    private Integer idCar;
    private String model;
    private String color;
    private Integer yearOfIssue;
    private String carNumber;

    public Car(Integer idCar, String model, String color, Integer yearOfIssue, String carNumber) {
        this.idCar = idCar;
        this.model = model;
        this.color = color;
        this.yearOfIssue = yearOfIssue;
        this.carNumber = carNumber;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
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

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
