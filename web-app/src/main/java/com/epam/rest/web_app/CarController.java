package com.epam.rest.web_app;

import com.epam.rest.model.Car;
import com.epam.rest.service.CarDtoService;
import com.epam.rest.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarDtoService carDtoService;

    private final CarService carService;

    //private final CarValidator carValidator;

    public CarController (CarDtoService carDtoService,
                          CarService carService){
        this.carDtoService = carDtoService;
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public final String cars (Model model) {
        model.addAttribute("cars", carDtoService.findByPrice());
        return "cars";
    }

    @GetMapping(value = "/car/{id}")
    public final String gotoEditCarPage (@PathVariable Integer id, Model model){
            logger.debug("gotoEditCarPage({})", model);
            model.addAttribute("isNew", true);
            model.addAttribute("car", carService.getCarById(id));
            return "car";
        }

    @GetMapping(value = "/car")
    public final String gotoAddCarPage(Model model) {
        logger.debug("gotoAddDCarPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("car", new Car());
        return "car";
    }

    @PostMapping(value = "/car")
    public String addCar (Car car) {

        logger.debug("addCar({}, {})", car);
        this.carService.create(car);
        return "redirect:/cars";
    }

    @PostMapping(value = "/car/{id}")
    public String updateCar(Car car) {

        logger.debug("updatecar({}, {})", car);
        this.carService.update(car);
        return "redirect:/cars";
    }

    @GetMapping(value = "/car/{id}/delete")
    public final String deleteCarById(@PathVariable Integer id, Model model) {

        logger.debug("delete({},{})", id, model);
        carService.delete(id);
        return "redirect:/cars";
    }
}
