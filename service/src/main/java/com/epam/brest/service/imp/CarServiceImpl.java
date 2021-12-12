package com.epam.brest.service.imp;

import com.epam.brest.dao.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service.CarService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CarServiceImpl implements CarService {

//    private final Logger logger = LogManager.getLogger(CarServiceImpl.class);

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
//        logger.trace("findAll()");
        return carDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Integer carId) {
//        logger.debug("Get car by id = {}", carId);
        return this.carDao.getCarById(carId);
    }
}