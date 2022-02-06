package com.epam.rest.web_app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);


    @GetMapping(value = "/customers")
    public final String customers(Model model) {
        return "customers";
    }

    @GetMapping(value = "/customer/{id}")
    public final String gotoEditCustomerPage(@PathVariable Integer id, Model model) {
        return "customers";
    }

    @GetMapping(value = "/customer/add")
    public final String gotoAddCustomerPage(Model model) {
        return "customer";
    }
}
