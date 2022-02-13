package com.epam.brest.rest.dao;

import com.epam.brest.model.Customer;
import com.epam.brest.testdb.SpringJdbcConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import({CustomerDaoJDBCImpl.class})
@PropertySource({"classpath:sql-customer.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class CustomerDaoJDBCImplIT {

    private final Logger logger = LogManager.getLogger(CustomerDaoJDBCImplIT.class);

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