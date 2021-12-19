package com.epam.brest.dao;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();

    Integer create(Car car);

    Integer update(Car car);

    Integer delete (Integer carId);

    Car getCarById(Integer carId);
}
