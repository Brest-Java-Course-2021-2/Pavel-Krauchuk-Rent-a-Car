package com.epam.rest.dao;

import com.epam.rest.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();

    Car getCarById(Integer carId);

    Integer create(Car car);

    Integer update(Car car);

    Integer delete (Integer carId);

}
