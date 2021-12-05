package com.epam.brest.web_app;

import com.epam.brest.service.CarService;
import com.epam.brest.web_app.validators.CarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    private final CarValidator carValidator;

    public CarController (CarService carService, CarValidator carValidator) {
        this.carService = carService;
        this.carValidator = carValidator;
    }



}
