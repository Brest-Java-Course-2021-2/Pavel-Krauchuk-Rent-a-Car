package com.epam.rest.dao;

import com.epam.rest.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomerDaoJDBCImpl implements CustomerDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_ALL_CUSTOMER}")
    private String sqlGetAllCustomer;

    @Value("${SQL_CREATE_CUSTOMER}")
    private String sqlCreateCustomer;

    @Value("${SQL_CUSTOMER_BY_ID}")
    private String sqlGetCustomerById;

    @Value("${SQL_UPDATE_CUSTOMER}")
    private String sqlUpdateCustomer;


    @Value("${SQL_DELETE_CUSTOMER_BY_ID}")
    private String sqlDeleteCustomerById;

    public CustomerDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Customer> findAllCustomer() {
        return namedParameterJdbcTemplate.query(sqlGetAllCustomer, new CustomerDaoJDBCImpl.CustomerRowMapper());
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("customerId", customerId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetCustomerById, sqlParameterSource, new CustomerDaoJDBCImpl.CustomerRowMapper());
    }

    @Override
    public Integer createCustomer(Customer customer) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", customer.getFirstName().toUpperCase())
                .addValue("lastName", customer.getLastName().toUpperCase())
                .addValue("address", customer.getAddress().toUpperCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreateCustomer, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer updateCustomer(Customer customer) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("customerId", customer.getCusomerId())
                .addValue("firstName", customer.getFirstName().toUpperCase())
                .addValue("lastName", customer.getLastName().toUpperCase())
                .addValue("address", customer.getAddress().toUpperCase());
        return namedParameterJdbcTemplate.update(sqlUpdateCustomer, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer customerId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("customerId", customerId);
        return namedParameterJdbcTemplate.update(sqlDeleteCustomerById, sqlParameterSource);
    }

    private class CustomerRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setCusomerId(resultSet.getInt("customer_id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setAddress(resultSet.getString("address"));
            return customer;
        }
    }
}
