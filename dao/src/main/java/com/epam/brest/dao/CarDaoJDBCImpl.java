package com.epam.brest.dao;

import com.epam.brest.model.Car;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CarDaoJDBCImpl implements CarDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoJDBCImpl (DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Integer create(Car car) {
        return null;
    }

    @Override
    public Integer update(Car car) {
        return null;
    }

    @Override
    public Integer delete(Car car) {
        return null;
    }
}
