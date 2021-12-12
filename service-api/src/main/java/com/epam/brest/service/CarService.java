package com.epam.brest.service;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarService {
    /**
     * Find all cars.
     *
     * @return cars list.
     */
    List<Car> findAll();

    Car getCarById (Integer carId);
}
