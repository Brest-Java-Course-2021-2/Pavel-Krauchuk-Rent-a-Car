package com.epam.brest.web_app;

import com.epam.brest.model.CarOrder;
import com.epam.brest.service.CarDtoService;
import com.epam.brest.service.CarService;
import com.epam.brest.web_app.validators.CarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.processing.Generated;

@Controller
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarDtoService carDtoService;

    private final CarService carService;

    private final CarValidator carValidator;

    public CarController (CarDtoService carDtoService,
                          CarService carService,
                          CarValidator carValidator){
        this.carDtoService = carDtoService;
        this.carService = carService;
        this.carValidator = carValidator;
    }

    @GetMapping(value = "/car/{id}")
    public final String gotoAddOrderCar (Model model){
            logger.debug("gotoAddDepartmentPage({})", model);
            model.addAttribute("isNew", true);
            model.addAttribute("order", new CarOrder());
            return "order";
        }
}
