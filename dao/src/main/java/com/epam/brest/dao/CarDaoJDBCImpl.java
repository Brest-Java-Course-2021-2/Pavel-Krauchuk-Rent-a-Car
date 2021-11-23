package com.epam.brest.dao;

import com.epam.brest.model.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarDaoJDBCImpl implements CarDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_ALL_CARS="select d.car_id, d.model, d.color, d.year_of_issue, d.car_number from car d order by d.model";
    private final String SQL_CREATE_CAR = "insert into car (model) values(:model)";

    public CarDaoJDBCImpl (DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {

        return namedParameterJdbcTemplate.query(SQL_ALL_CARS, new CarRowMapper());
    }

    @Override
    public Integer create(Car car) {

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("model", car.getModel());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_CAR, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Car car) {
        return null;
    }

    @Override
    public Integer delete(Car car) {
        return null;
    }

    private class CarRowMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt("car_id"));
            car.setModel(resultSet.getString("model"));
            car.setColor(resultSet.getString("color"));
            car.setYearOfIssue(resultSet.getInt("year_of_issue"));
            car.setCarNumber(resultSet.getString("car_number"));
            return car;
        }
    }
}
