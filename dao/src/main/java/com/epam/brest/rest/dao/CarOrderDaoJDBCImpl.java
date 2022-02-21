package com.epam.brest.rest.dao;

import com.epam.brest.model.CarOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class CarOrderDaoJDBCImpl implements CarOrderDao{

    private final Logger logger = LogManager.getLogger(CarOrderDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_ALL_ORDER}")
    private String sqlGetAllOrder;

    @Value("${SQL_CREATE_ORDER}")
    private String sqlCreateOrder;

    @Value("${SQL_ORDER_BY_ID}")
    private String sqlGetOrderById;

    @Value("${SQL_UPDATE_ORDER}")
    private String sqlUpdateOrder;

    @Value("${SQL_DELETE_ORDER_BY_ID}")
    private String sqlDeleteOrderById;

    public CarOrderDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<CarOrder> findAllOrder() {
        logger.debug("Start: order findAll()");
        return namedParameterJdbcTemplate.query(sqlGetAllOrder, new CarOrderRowMapper());
    }

    @Override
    public CarOrder getOrderById(Integer orderId) {
        logger.debug("Get order by id = {}", orderId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("orderId", orderId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetOrderById, sqlParameterSource, new CarOrderDaoJDBCImpl.CarOrderRowMapper());
    }

    @Override
    public Integer createOrder(CarOrder carOrder) {
        logger.debug("Create order: {}", carOrder);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("dateOrder", carOrder.getDateOrder())
                .addValue("customer", carOrder.getCustomer())
                .addValue("carId", carOrder.getCarId())
                .addValue("rentalPeriod", carOrder.getRentalPeriod());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreateOrder, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer updateOrder(CarOrder carOrder) {
        logger.debug("Update order: {}", carOrder);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("orderId", carOrder.getOrderId())
                .addValue("dateOrder", carOrder.getDateOrder())
                .addValue("customer", carOrder.getCustomer())
                .addValue("carId", carOrder.getCarId())
                .addValue("rentalPeriod", carOrder.getRentalPeriod());
        return namedParameterJdbcTemplate.update(sqlUpdateOrder, sqlParameterSource);
    }

    @Override
    public Integer deleteOrder(Integer orderId) {
        logger.debug("Delete order: {}", orderId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("orderId", orderId);
        return namedParameterJdbcTemplate.update(sqlDeleteOrderById, sqlParameterSource);
    }


    private class CarOrderRowMapper implements RowMapper<CarOrder> {

        @Override
        public CarOrder mapRow(ResultSet resultSet, int i) throws SQLException {
            CarOrder carOrder = new CarOrder();
            carOrder.setOrderId(resultSet.getInt("order_id"));
            carOrder.setDateOrder(resultSet.getTimestamp("date_order").toInstant());
            carOrder.setCustomer(resultSet.getString("customer"));
            carOrder.setCarId(resultSet.getInt("car_id"));
            carOrder.setRentalPeriod(resultSet.getInt("rental_period"));
            return carOrder;
        }
    }
}
