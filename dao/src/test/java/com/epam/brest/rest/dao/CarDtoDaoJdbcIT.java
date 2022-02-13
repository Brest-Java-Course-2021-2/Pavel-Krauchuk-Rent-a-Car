package com.epam.brest.rest.dao;

import com.epam.brest.testdb.SpringJdbcConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import({CarDtoDaoJdbc.class})
@PropertySource({"classpath:sql-car.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class CarDtoDaoJdbcIT {

    private final Logger logger = LogManager.getLogger(CarDtoDaoJdbcIT.class);

    private CarDtoDaoJdbc carDtoDao;

    public CarDtoDaoJdbcIT(@Autowired CarDtoDao carDtoDao) {
        this.carDtoDao = (CarDtoDaoJdbc) carDtoDao;
    }

    @Test
    void findByPrice() {
        logger.debug("Execute test: findByPrice()");
        assertNotNull(carDtoDao);
        assertNotNull(carDtoDao.findByPrice());
    }
}