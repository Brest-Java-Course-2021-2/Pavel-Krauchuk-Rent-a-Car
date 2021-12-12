package com.epam.brest.web_app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping(value = "/order")
    public String cars (Model model){
        return "order";
    }
}
