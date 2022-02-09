package com.epam.brest.rest;

import com.epam.brest.rest.dao.CarDaoJDBCImpl;
import com.epam.brest.model.dto.CarDto;
import com.epam.brest.service.CarDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CarDtoController {

    private static final Logger logger = LogManager.getLogger(CarDaoJDBCImpl.class);

    private final CarDtoService carDtoService;

    public CarDtoController(CarDtoService carDtoService) {
        this.carDtoService = carDtoService;
    }

    @GetMapping(value = "/cars_dtos")
    public final Collection<CarDto> getCarById(@PathVariable Integer id) {
        logger.debug("carDtos()");
        return carDtoService.findByPrice();
    }
}
