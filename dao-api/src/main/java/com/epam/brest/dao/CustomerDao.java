package com.epam.brest.dao;

import com.epam.brest.model.Car;
import com.epam.brest.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    Integer create(Car car);

    Integer update(Car car);

    Integer delete (Car car);
}
