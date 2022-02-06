package com.epam.rest;

import com.epam.rest.model.dto.CarDto;
import com.epam.rest.service.CarDtoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CarDtoController {

    private final CarDtoService carDtoService;

    public CarDtoController(CarDtoService carDtoService) {
        this.carDtoService = carDtoService;
    }

    @GetMapping(value = "/cars_dtos")
    public final Collection<CarDto> getCarById(@PathVariable Integer id) {

        return carDtoService.findByPrice();
    }
}
