package com.epam.rest;

import com.epam.rest.model.Car;
import com.epam.rest.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public final Collection<Car> cars() {
    return carService.findAll();
    }


    @GetMapping(value = "/cars/{id}")
    public final Car getCarById(@PathVariable Integer id) {
    Car car = carService.getCarById(id);
    return car;
    }

    @PostMapping(path = "/cars", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createCar(@RequestBody Car car) {

        Integer id = carService.create(car);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/cars", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateCar(@RequestBody Car car) {
        int result = carService.update(car);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteCar(@PathVariable Integer id) {

        int result = carService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
