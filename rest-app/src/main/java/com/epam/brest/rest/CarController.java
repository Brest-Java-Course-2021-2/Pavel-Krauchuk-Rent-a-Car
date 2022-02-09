package com.epam.brest.rest;

import com.epam.brest.rest.dao.CarDaoJDBCImpl;
import com.epam.brest.model.Car;
import com.epam.brest.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

@RestController
public class CarController {

    private static final Logger logger = LogManager.getLogger(CarDaoJDBCImpl.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")

    public final Collection<Car> cars() {
    logger.debug("cars()");
    return carService.findAll();
    }


    @GetMapping(value = "/cars/{id}")
    public final Car getCarById(@PathVariable Integer id) {
        logger.debug("getCarById({})", id);
    Car car = carService.getCarById(id);
    return car;
    }

    @PostMapping(path = "/cars", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createCar(@RequestBody Car car) {

        logger.debug("createCar({})", car);
        Integer id = carService.create(car);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/cars", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateCar(@RequestBody Car car) {
        logger.debug("updateCar({})", car);
        int result = carService.update(car);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteCar(@PathVariable Integer id) {
        logger.debug("deleteCar({})", id);
        int result = carService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
