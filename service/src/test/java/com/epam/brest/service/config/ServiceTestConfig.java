package com.epam.brest.service.config;


import com.epam.brest.rest.dao.CarDao;
import com.epam.brest.rest.dao.CarDaoJDBCImpl;
import com.epam.brest.rest.dao.CarDtoDao;
import com.epam.brest.rest.dao.CarDtoDaoJdbc;
import com.epam.brest.service.CarDtoService;
import com.epam.brest.service.CarService;
import com.epam.brest.service.impl.CarDtoServiceImpl;
import com.epam.brest.service.impl.CarServiceImpl;
import com.epam.brest.testdb.SpringJdbcConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceTestConfig extends SpringJdbcConfig{
    @Bean
    CarDtoDao carDtoDao() {
        return new CarDtoDaoJdbc(namedParameterJdbcTemplate());
    }

    @Bean
    CarDtoService carDtoService() {
        return new CarDtoServiceImpl(carDtoDao());
    }

    @Bean
    CarDao carDao() {
        return new CarDaoJDBCImpl(namedParameterJdbcTemplate());
    }

    @Bean
    CarService carService() {
        return new CarServiceImpl(carDao());
    }
}
