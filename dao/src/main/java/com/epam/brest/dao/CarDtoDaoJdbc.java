package com.epam.brest.dao;

import com.epam.brest.model.dto.CarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class CarDtoDaoJdbc implements CarDtoDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${findAllWithAvgPriceSql}")
    private String findAllWithAvgPriceSql;

    public CarDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarDto> findTotalPrice() {
        List<CarDto> cars = namedParameterJdbcTemplate.query(findAllWithAvgPriceSql, BeanPropertyRowMapper.newInstance(CarDto.class));
        return cars;
    }
}






