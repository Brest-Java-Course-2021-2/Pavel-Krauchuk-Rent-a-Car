package com.epam.brest.service.impl;

import com.epam.brest.service.exceptions.CarNotFoundException;
import com.epam.brest.rest.dao.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final Logger logger = LogManager.getLogger(CarServiceImpl.class);

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Integer carId) {
        logger.debug("Get car by id = {}", carId);
        try {
            return this.carDao.getCarById(carId);
        } catch (EmptyResultDataAccessException e) {
            throw new CarNotFoundException(carId);
        }
    }

    @Override
    @Transactional
    public Integer create(Car car) {
        logger.debug("create({})", car);
        return this.carDao.create(car);
    }

    @Override
    @Transactional
    public Integer update(Car car) {
        logger.debug("create({})", car);
        return this.carDao.update(car);
    }

    @Override
    @Transactional
    public Integer delete(Integer carId) {
        logger.debug("delete car with id = {}", carId);
        return this.carDao.delete(carId);
    }

    @Override
    public List<Car> findAll() {
        logger.trace("findAll()");
        return carDao.findAll();
    }
}
