package com.epam.brest.rest.dao;

import com.epam.brest.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAllCustomer();

    Customer getCustomerById(Integer customerId);

    Integer createCustomer(Customer customer);

    Integer updateCustomer(Customer customer);

    Integer delete (Integer customerId);
}
