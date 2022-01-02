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
import org.springframework.test.util.ReflectionTestUtils;

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

    @AfterEach
    public void check() {
        Mockito.verifyNoInteractions(namedParameterJdbcTemplate);
    }

    @Test
    void findAll() {
        String sql = "select";
        ReflectionTestUtils.setField(carDaoJDBC, "sqlGetAllCars", sql);
        Car car = new Car("TEST");
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
        Car car = new Car("TEST CAR");

        Mockito.when(namedParameterJdbcTemplate.queryForObject(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<RowMapper<Car>>any())
        ).thenReturn(car);

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

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}