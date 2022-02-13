package com.epam.brest.service.impl;

import com.epam.brest.model.dto.CarDto;
import com.epam.brest.service.config.ServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:sql-car.properties"})
@Transactional
class CarDtoServiceImplIT {

    @Autowired
    CarDtoServiceImpl carDtoService;

    @Test
    public void shouldFindByPrice() {
        List<CarDto> cars = carDtoService.findByPrice();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
        assertTrue(cars.get(0).getPrice().intValue() > 0);
    }

}