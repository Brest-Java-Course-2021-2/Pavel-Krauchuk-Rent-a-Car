package com.epam.brest.web_app;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @GetMapping(value = "/orders")
    public final String orders(Model model) {
        return "orders";
    }

    @GetMapping(value = "/order/{id}")
    public final String gotoEditOrderPage(@PathVariable Integer id, Model model) {
        return "order";
    }

    @GetMapping(value = "/order/add")
    public final String gotoAddOrderPage(Model model) {
        return "order";
    }

}
