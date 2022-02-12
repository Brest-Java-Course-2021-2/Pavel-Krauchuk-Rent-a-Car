package com.epam.brest.rest.dao;

import com.epam.brest.model.Car;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import({CarDaoJDBCImpl.class})
@PropertySource({"classpath:sql-car.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class CarDaoJDBCImplTestIT {

    private final Logger logger = LogManager.getLogger(CarDaoJDBCImplTestIT.class);

    private final CarDaoJDBCImpl carDaoJDBC;

    public CarDaoJDBCImplTestIT(@Autowired CarDao carDaoJDBC) {
        this.carDaoJDBC = (CarDaoJDBCImpl) carDaoJDBC;
    }
    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
        assertNotNull(carDaoJDBC);
        assertNotNull(carDaoJDBC.findAll());
    }
   @Test
   void create() {
       logger.debug("Execute test: create()");
       assertNotNull(carDaoJDBC);
       Integer carSizeBefore = carDaoJDBC.findAll().size();
       Car car = new Car("KIA", BigDecimal.valueOf(14.49));
       Integer newCarId = carDaoJDBC.create(car);
       assertNotNull(newCarId);
       assertEquals((int) carSizeBefore, carDaoJDBC.findAll().size() - 1);
   }

   @Test
   void getCarById() {
       logger.debug("Execute test: getCarById()");
       List<Car> cars = carDaoJDBC.findAll();
       if (cars.size()==0){
           carDaoJDBC.create(new Car("Model T", BigDecimal.valueOf(99.99)));
           cars = carDaoJDBC.findAll();
       }
       Car carSrc = cars.get(0);
       Car carDst = carDaoJDBC.getCarById(carSrc.getCarId());
       assertEquals(carSrc.getModel(), carDst.getModel());
   }

   @Test
   void update() {
       logger.debug("Execute test: update()");
       List<Car> cars = carDaoJDBC.findAll();
       if (cars.size()==0){
           carDaoJDBC.create(new Car("Model T", BigDecimal.valueOf(99.99)));
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
       logger.debug("Execute test: delete()");
       carDaoJDBC.create(new Car("Model T", BigDecimal.valueOf(99.99)));
       List<Car> cars = carDaoJDBC.findAll();
       carDaoJDBC.delete(cars.get(cars.size()-1).getCarId());
       assertEquals(cars.size()-1, carDaoJDBC.findAll().size());
   }
}