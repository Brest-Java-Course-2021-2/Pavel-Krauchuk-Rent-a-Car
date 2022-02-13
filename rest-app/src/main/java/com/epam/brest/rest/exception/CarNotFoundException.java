package com.epam.brest.rest.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Integer id){
        super("Car not found for id:" + id);
    }
}
