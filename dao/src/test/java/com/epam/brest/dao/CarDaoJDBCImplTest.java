package com.epam.brest.dao;

import com.epam.brest.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
class CarDaoJDBCImplTest {
    private final Logger logger = LogManager.getLogger(CarDaoJDBCImplTest.class);
    private CarDaoJDBCImpl carDaoJDBC;

    public CarDaoJDBCImplTest(@Autowired CarDao carDaoJDBC) {

        this.carDaoJDBC = (CarDaoJDBCImpl) carDaoJDBC;
    }

    @Test
    void findAll() {
        assertNotNull(carDaoJDBC);
        assertNotNull(carDaoJDBC.findAll());
    }
    @Test
    void create() {
        assertNotNull(carDaoJDBC);
        Integer carSizeBefore = carDaoJDBC.findAll().size();
        Car car = new Car(32000,"SKODA", "GREEN");
        Integer newCarId = carDaoJDBC.create(car);
        assertNotNull(newCarId);
        assertEquals((int) carSizeBefore, carDaoJDBC.findAll().size() - 1);
    }

    @Test
    void getCarById() {
        List<Car> cars = carDaoJDBC.findAll();
        if (cars.size()==0){
            carDaoJDBC.create(new Car("Model T"));
            cars = carDaoJDBC.findAll();
        }
        Car carSrc = cars.get(0);
        Car carDst = carDaoJDBC.getCarById(carSrc.getCarId());
        assertEquals(carSrc.getModel(), carDst.getModel());
    }

    @Test
    void update() {
        List<Car> cars = carDaoJDBC.findAll();
        if (cars.size()==0){
            carDaoJDBC.create(new Car("Model T"));
            cars = carDaoJDBC.findAll();
        }
        Car carSrc = cars.get(0);
        carSrc.setModel(carSrc.getModel()+"_TEST");
        carDaoJDBC.update(carSrc);

        Car carDrt = carDaoJDBC.getCarById(carSrc.getCarId());
        assertEquals(carSrc.getModel(), carDrt.getModel());
    }

    @Test
    void delete() {
        carDaoJDBC.create(new Car("Model T"));
        List<Car> cars = carDaoJDBC.findAll();
        carDaoJDBC.delete(cars.get(cars.size()-1).getCarId());
        assertEquals(cars.size()-1, carDaoJDBC.findAll().size());
    }
}