package com.epam.brest.dao;

import com.epam.brest.model.dto.CarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class CarDtoDaoJdbc implements CarDtoDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String findAllWithAvgPriceSql = "SELECT\n" +
            "\td.car_id AS carId,\n" +
            "\td.model AS model,\n" +
            "\td.price AS price\n";


    public CarDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarDto> findAllWithAvgPrice() {
        List<CarDto> cars = namedParameterJdbcTemplate.query(findAllWithAvgPriceSql, BeanPropertyRowMapper.newInstance(CarDto.class));
        return cars;
    }
}
