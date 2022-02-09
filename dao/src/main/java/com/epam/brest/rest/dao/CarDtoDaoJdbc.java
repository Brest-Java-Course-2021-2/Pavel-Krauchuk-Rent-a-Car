package com.epam.brest.rest.dao;

import com.epam.brest.model.dto.CarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class CarDtoDaoJdbc implements CarDtoDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${findByPriceSql}")
    private String findByPriceSql;

    public CarDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarDto> findByPrice() {
        List<CarDto> cars = namedParameterJdbcTemplate.query(findByPriceSql, BeanPropertyRowMapper.newInstance(CarDto.class));
        return cars;
    }
}






