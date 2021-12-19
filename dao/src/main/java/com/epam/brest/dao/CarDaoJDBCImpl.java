package com.epam.brest.dao;

import com.epam.brest.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarDaoJDBCImpl implements CarDao{

    private final Logger logger = LogManager.getLogger(CarDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_ALL_CARS}")
    public String sqlGetAllCars;

    @Value("${SQL_CREATE_CAR}")
    public String sqlCreateCar;

    @Value("${SQL_CAR_BY_ID}")
    public String sqlGetCarById;

    @Value("${SQL_UPDATE_MODEL}")
    private String sqlUpdateModel;

    @Value("4{SQL_DELETE_CAR_BY_ID")
    private String sqlDeleteCarById;


    public CarDaoJDBCImpl (DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        logger.debug("DEBUG MASSAGE");

        return namedParameterJdbcTemplate.query(sqlGetAllCars, new CarRowMapper());
    }

    @Override
    public Car getCarById(Integer carId) {
        logger.debug("Get car by id = {}", carId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("carId", carId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetCarById, sqlParameterSource, new CarRowMapper());
    }

    @Override
    public Integer create(Car car) {
        logger.debug("Start: creat({})", car);

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("model", car.getModel().toUpperCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreateCar, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Car car) {
        logger.debug("Update car: {}", car);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("model", car.getModel()).addValue("carId", car.getCarId());
        return namedParameterJdbcTemplate.update(sqlUpdateModel, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer carId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("carId", carId);
        return namedParameterJdbcTemplate.update(sqlDeleteCarById, sqlParameterSource);
    }

    private class CarRowMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt("car_id"));
            car.setModel(resultSet.getString("model"));
            car.setColor(resultSet.getString("color"));
            return car;
        }
    }
}
