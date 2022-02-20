package com.epam.brest.rest.dao;

import com.epam.brest.model.CarOrder;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import({CarOrderDaoJDBCImpl.class})
@PropertySource({"classpath:sql-order.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class CarOrderDaoJDBCImplIT {

    private final Logger logger = LogManager.getLogger(CarDaoJDBCImplTestIT.class);

    private final CarOrderDaoJDBCImpl carOrderDaoJDBC;

    CarOrderDaoJDBCImplIT(@Autowired CarOrderDao carOrderDaoJDBC) {
        this.carOrderDaoJDBC = (CarOrderDaoJDBCImpl) carOrderDaoJDBC;
    }

    @Test
    void findAllOrder() {
        logger.debug("Execute test: findAll()");
        assertNotNull(carOrderDaoJDBC);
        assertNotNull(carOrderDaoJDBC.findAllOrder());
    }

    @Test
    void findOrderById() {
    }

    @Test
    void createOrder() {
        logger.debug("Execute test: create()");
        assertNotNull(carOrderDaoJDBC);
        Integer carOrderSizeBefore = carOrderDaoJDBC.findAllOrder().size();
        CarOrder carOrder = new CarOrder("JAMES BOND", 1, 1);
        Integer newOrderId = carOrderDaoJDBC.createOrder(carOrder);
        assertNotNull(newOrderId);
        assertEquals((int) carOrderSizeBefore, carOrderDaoJDBC.findAllOrder().size() - 1);
    }

    @Test
    void updateOrder() {
        logger.debug("Execute test: update()");
        List<CarOrder> orders = carOrderDaoJDBC.findAllOrder();
        if (orders.size()==0){
            carOrderDaoJDBC.createOrder(new CarOrder("JAMES BOND", 1, 1));
            orders = carOrderDaoJDBC.findAllOrder();
        }
        CarOrder orderSrc = orders.get(0);
        orderSrc.setCustomer(orderSrc.getCustomer()+"_TEST");
        carOrderDaoJDBC.updateOrder(orderSrc);
        Integer id = orderSrc.getOrderId();
        CarOrder orderDrt = carOrderDaoJDBC.getOrderById(id);
        assertEquals(orderSrc.getCustomer(), orderDrt.getCustomer());

    }

    @Test
    void deleteOrder() {
        logger.debug("Execute test: delete order()");
        carOrderDaoJDBC.createOrder(new CarOrder("JAMES BOND", 1, 1));
        List<CarOrder> orders = carOrderDaoJDBC.findAllOrder();
        carOrderDaoJDBC.deleteOrder(orders.get(orders.size()-1).getOrderId());
        assertEquals(orders.size()-1, carOrderDaoJDBC.findAllOrder().size());
    }
}