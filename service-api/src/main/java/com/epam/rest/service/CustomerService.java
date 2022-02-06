package com.epam.rest.service;

import com.epam.rest.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomer();

    Customer getCustomerById(Integer carId);

    Integer createCustomer(Customer customer);

    Integer updateCustomer(Customer customer);

    Integer deleteCustomer (Integer customerId);
}
