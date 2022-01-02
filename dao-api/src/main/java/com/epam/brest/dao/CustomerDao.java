package com.epam.brest.dao;

import com.epam.brest.model.Car;
import com.epam.brest.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    Integer create(Customer customer);

    Integer update(Customer customer);

    Integer delete (Integer customerId);
}
