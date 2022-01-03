package com.epam.brest.service.impl;

import com.epam.brest.dao.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarsServiceImpl implements CarService {

    private final CarDao carDao;

    public CarsServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car getCarById(Integer carId) {
        return null;
    }
}
