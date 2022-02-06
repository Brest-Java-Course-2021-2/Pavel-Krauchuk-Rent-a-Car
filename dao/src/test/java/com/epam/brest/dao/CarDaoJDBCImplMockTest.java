package com.epam.brest.dao;

import com.epam.brest.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CarDaoJDBCImplMockTest {

    @InjectMocks
    private CarDaoJDBCImpl carDaoJDBC;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Car>> captorMapper;

    @Captor
    private ArgumentCaptor<SqlParameterSource> captorSource;

    @Captor
    private ArgumentCaptor<GeneratedKeyHolder> captorKeyHolder;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    void findAll() {
        String sql = "select";
        ReflectionTestUtils.setField(carDaoJDBC, "sqlGetAllCars", sql);
        Car car = new Car();
        List<Car> list = Collections.singletonList(car);
        Mockito.when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Car>>any())).thenReturn(list);
        List<Car> result = carDaoJDBC.findAll();
        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captorMapper.capture());
        RowMapper<Car> mapper = captorMapper.getValue();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(car, result.get(0));
    }

    @Test
    void getCarById() {
        String sql = "get";
        ReflectionTestUtils.setField(carDaoJDBC, "sqlGetCarById", sql);
        int id = 0;
        Car car = new Car();
        Mockito.when(namedParameterJdbcTemplate.queryForObject(any(), ArgumentMatchers.<SqlParameterSource>any(), ArgumentMatchers.<RowMapper<Car>>any())).thenReturn(car);
        Car result = carDaoJDBC.getCarById(id);
        Mockito.verify(namedParameterJdbcTemplate)
                .queryForObject(eq(sql), captorSource.capture(), captorMapper.capture());
        SqlParameterSource source = captorSource.getValue();
        RowMapper<Car> mapper = captorMapper.getValue();
        Assertions.assertNotNull(source);
        Assertions.assertNotNull(mapper);
        Assertions.assertNotNull(result);
        Assertions.assertSame(car, result);
    }

    @Test
    void create() {
        String sql = "create";
        ReflectionTestUtils.setField(carDaoJDBC, "sqlCreateCar", sql);
        Car car = new Car("KIA", BigDecimal.valueOf(14.49));
        Integer id = 0;
        Mockito.when(namedParameterJdbcTemplate.update(any(), ArgumentMatchers.<SqlParameterSource>any(), ArgumentMatchers.<KeyHolder>any())).thenReturn(id);
        Integer result = carDaoJDBC.create(car);
        Mockito.verify(namedParameterJdbcTemplate).update(eq(sql),captorSource.capture(), captorKeyHolder.capture());
        SqlParameterSource source = captorSource.getValue();
        KeyHolder keyHolder = captorKeyHolder.getValue();
        Assertions.assertNotNull(source);
        Assertions.assertNotNull(keyHolder);
    }

    @Test
    void update() {
        String sql = "update";
        ReflectionTestUtils.setField(carDaoJDBC, "sqlUpdateModel", sql);
        Car car = new Car("KIA", BigDecimal.valueOf(14.49));
        Integer id = 0;
        Mockito.when(namedParameterJdbcTemplate.update(any(), ArgumentMatchers.<SqlParameterSource>any())).thenReturn(id);
        Integer result = carDaoJDBC.update(car);
        Mockito.verify(namedParameterJdbcTemplate).update(eq(sql),captorSource.capture());
        SqlParameterSource source = captorSource.getValue();
        Assertions.assertNotNull(source);
    }

    @Test
    void delete() {
        String sql = "delete";
        ReflectionTestUtils.setField(carDaoJDBC, "sqlDeleteCarById", sql);
        int id = 0;
        Mockito.when(namedParameterJdbcTemplate.update(any(), ArgumentMatchers.<SqlParameterSource>any())).thenReturn(id);
        Integer result = carDaoJDBC.delete(id);
        Mockito.verify(namedParameterJdbcTemplate).update(eq(sql),captorSource.capture());
        SqlParameterSource source = captorSource.getValue();
        Assertions.assertNotNull(source);
    }
}