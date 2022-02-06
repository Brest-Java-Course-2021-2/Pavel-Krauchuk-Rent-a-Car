package com.epam.rest.dao;

import com.epam.rest.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAllCustomer();

    Customer getCustomerById(Integer customerId);

    Integer createCustomer(Customer customer);

    Integer updateCustomer(Customer customer);

    Integer delete (Integer customerId);
}
