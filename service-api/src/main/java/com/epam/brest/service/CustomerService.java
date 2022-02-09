package com.epam.brest.service;

import com.epam.brest.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomer();

    Customer getCustomerById(Integer carId);

    Integer createCustomer(Customer customer);

    Integer updateCustomer(Customer customer);

    Integer deleteCustomer (Integer customerId);
}
