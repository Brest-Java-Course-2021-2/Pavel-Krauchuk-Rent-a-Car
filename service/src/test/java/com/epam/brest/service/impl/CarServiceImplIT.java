package com.epam.brest.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class CarServiceImplIT {

//    @Autowired
//    CarService carService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCarById() {
    }

    @Test
    void create() {
//        assertNotNull(carService);
//        Integer carSizeBefore = carService.findAll().size();
//        assertNotNull(carSizeBefore);
//        Car car = new Car("MODEL T", BigDecimal.valueOf(99.99));
//        Integer newCarId = carService.create(car);
//        assertNotNull(newCarId);
//        assertEquals(carSizeBefore, carService.findAll().size() - 1);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}