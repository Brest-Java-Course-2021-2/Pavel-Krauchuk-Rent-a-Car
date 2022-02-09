package com.epam.brest.rest.dao;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();

    Car getCarById(Integer carId);

    Integer create(Car car);

    Integer update(Car car);

    Integer delete (Integer carId);

}
