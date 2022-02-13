package com.epam.brest.service.impl;

import com.epam.brest.model.Car;
import com.epam.brest.service.config.ServiceTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:sql-car.properties"})
@Transactional
class CarServiceImplIT {

    private final Logger logger = LogManager.getLogger(CarServiceImplIT.class);

    @Autowired
    CarServiceImpl carService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCarById() {
        logger.debug("Execute test: getCarById()");
        List<Car> cars = carService.findAll();
        if (cars.size()==0){
            carService.create(new Car("Model T", BigDecimal.valueOf(99.99)));
            cars = carService.findAll();
        }
        Car carSrc = cars.get(0);
        Car carDst = carService.getCarById(carSrc.getCarId());
        assertEquals(carSrc.getModel(), carDst.getModel());
    }

    @Test
    void create() {
        logger.debug("Execute test: CarService.create()");
        assertNotNull(carService);
        Integer carSizeBefore = carService.findAll().size();
        assertNotNull(carSizeBefore);
        Car car = new Car("MODEL T", BigDecimal.valueOf(99.99));
        Integer newCarId = carService.create(car);
        assertNotNull(newCarId);
        assertEquals(carSizeBefore, carService.findAll().size() - 1);
    }

    @Test
    void update() {
        logger.debug("Execute test: carService.update()");
        List<Car> cars = carService.findAll();
        if (cars.size()==0){
            carService.create(new Car("Model T", BigDecimal.valueOf(99.99)));
            cars = carService.findAll();
        }
        Car carSrc = cars.get(0);
        carSrc.setModel(carSrc.getModel()+"_TEST");
        carService.update(carSrc);

        Car carDrt = carService.getCarById(carSrc.getCarId());
        assertEquals(carSrc.getModel(), carDrt.getModel());
    }

    @Test
    void delete() {
        logger.debug("Execute test: delete()");
        carService.create(new Car("Model T", BigDecimal.valueOf(99.99)));
        List<Car> cars = carService.findAll();
        carService.delete(cars.get(cars.size()-1).getCarId());
        assertEquals(cars.size()-1, carService.findAll().size());
    }

    @Test
    void findAll() {
        logger.debug("Execute test: CarService.findAll()");
        assertNotNull(carService);
        assertNotNull(carService.findAll());
    }
}