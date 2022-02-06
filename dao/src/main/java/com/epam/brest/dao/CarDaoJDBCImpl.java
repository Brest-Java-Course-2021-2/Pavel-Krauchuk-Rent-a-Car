package com.epam.brest.dao;

import com.epam.brest.model.Car;
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
public class CarDaoJDBCImpl implements CarDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_ALL_CARS}")
    private String sqlGetAllCars;

    @Value("${SQL_CREATE_CAR}")
    private String sqlCreateCar;

    @Value("${SQL_CAR_BY_ID}")
    private String sqlGetCarById;

    @Value("${SQL_UPDATE_MODEL}")
    private String sqlUpdateModel;

    @Value("${SQL_DELETE_CAR_BY_ID}")
    private String sqlDeleteCarById;

    public CarDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        return namedParameterJdbcTemplate.query(sqlGetAllCars, new CarRowMapper());
    }

    @Override
    public Car getCarById(Integer carId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("carId", carId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetCarById, sqlParameterSource, new CarRowMapper());
    }

    @Override
    public Integer create(Car car) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("model", car.getModel())
            .addValue("price", car.getPrice());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreateCar, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Car car) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("carId", car.getCarId())
                .addValue("model", car.getModel())
                .addValue("price", car.getPrice());
        return namedParameterJdbcTemplate.update(sqlUpdateModel, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer carId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("carId", carId);
        return namedParameterJdbcTemplate.update(sqlDeleteCarById, sqlParameterSource);
    }

    private class CarRowMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt("car_id"));
            car.setModel(resultSet.getString("model"));
            car.setPrice(resultSet.getBigDecimal("price"));
            return car;
        }
    }
}
