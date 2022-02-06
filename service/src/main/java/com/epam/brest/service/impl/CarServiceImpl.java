package com.epam.brest.service.impl;

import com.epam.brest.dao.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Car getCarById(Integer carId) {
        return this.carDao.getCarById(carId);
    }

    @Override
    @Transactional
    public Integer create(Car car) {
        return this.carDao.create(car);
    }

    @Override
    public Integer update(Car car) {
        return this.carDao.update(car);
    }

    @Override
    public Integer delete(Integer carId) {
        return this.carDao.delete(carId);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }
}
