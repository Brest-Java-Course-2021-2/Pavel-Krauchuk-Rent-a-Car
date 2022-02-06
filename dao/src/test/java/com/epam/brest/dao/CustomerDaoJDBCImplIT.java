package com.epam.brest.dao;

import com.epam.brest.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
class CustomerDaoJDBCImplIT {

    private final CustomerDaoJDBCImpl customerDaoJDBC;

    public CustomerDaoJDBCImplIT (@Autowired CustomerDao customerDaoJDBC) {
        this.customerDaoJDBC = (CustomerDaoJDBCImpl) customerDaoJDBC;
    }
    @Test
    void findAllCustomer() {
        assertNotNull(customerDaoJDBC);
        assertNotNull(customerDaoJDBC.findAllCustomer());
    }

    @Test
    void getCustomerById() {
        List<Customer> customers = customerDaoJDBC.findAllCustomer();
        if (customers.size()==0){
            customerDaoJDBC.createCustomer(new Customer("SERGY", "SERGEEV", "MASHEROVA 12-30"));
            customers = customerDaoJDBC.findAllCustomer();
        }
        Customer customerSrc = customers.get(0);
        Customer customerDst = customerDaoJDBC.getCustomerById(customerSrc.getCusomerId());
        assertEquals(customerSrc.getFirstName(), customerDst.getFirstName());
    }

    @Test
    void createCustomer() {
        assertNotNull(customerDaoJDBC);
        Integer carSizeBefore = customerDaoJDBC.findAllCustomer().size();
        Customer customer = new Customer("SERGY", "SERGEEV", "MASHEROVA 12-30");
        Integer newCustomerId = customerDaoJDBC.createCustomer(customer);
        assertNotNull(newCustomerId);
        assertEquals((int) carSizeBefore, customerDaoJDBC.findAllCustomer().size() - 1);
    }

    @Test
    void updateCustomer() {
        List<Customer> customers = customerDaoJDBC.findAllCustomer();
        if (customers.size()==0){
            customerDaoJDBC.createCustomer(new Customer("SERGY", "SERGEEV", "MASHEROVA 12-30"));
            customers = customerDaoJDBC.findAllCustomer();
        }
        Customer customerSrc = customers.get(0);
        customerSrc.setFirstName(customerSrc.getFirstName() + "_TEST");
        customerSrc.setLastName(customerSrc.getLastName() + "_TEST");
        customerSrc.setAddress(customerSrc.getAddress()+"_TEST");
        customerDaoJDBC.updateCustomer(customerSrc);
        Customer customerDrt = customerDaoJDBC.getCustomerById(customerSrc.getCusomerId());
        assertEquals(customerSrc.getFirstName(), customerDrt.getFirstName());
    }

    @Test
    void delete() {
        customerDaoJDBC.createCustomer(new Customer(99,"SERGY", "SERGEEV", "MASHEROVA 12-30"));
        List<Customer> customers = customerDaoJDBC.findAllCustomer();
        customerDaoJDBC.delete(customers.get(customers.size()-1).getCusomerId());
        assertEquals(customers.size()-1, customerDaoJDBC.findAllCustomer().size());
    }
}