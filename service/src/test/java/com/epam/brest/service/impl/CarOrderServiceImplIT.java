package com.epam.brest.service.impl;

import com.epam.brest.model.CarOrder;
import com.epam.brest.service.config.ServiceTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:sql-order.properties"})
@Transactional
class CarOrderServiceImplIT {

    private final Logger logger = LogManager.getLogger(CarOrderServiceImplIT.class);

    @Autowired
    CarOrderServiceImpl carOrderService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCarOrderById() {
        logger.debug("Execute test: getCarOrderById()");
        List<CarOrder> orders = carOrderService.findAllOrder();
        if (orders.size()==0){
            carOrderService.createOrder(new CarOrder("JAMES BOND", 1, 1));
            orders = carOrderService.findAllOrder();
        }
        CarOrder orderSrc = orders.get(0);
        CarOrder orderDst = carOrderService.getOrderById(orderSrc.getOrderId());
        assertEquals(orderSrc.getCustomer(), orderDst.getCustomer());
    }

    @Test
    void createOrder() {
        logger.debug("Execute test: CarOrderService.create()");
        assertNotNull(carOrderService);
        Integer carOrderSizeBefore = carOrderService.findAllOrder().size();
        assertNotNull(carOrderSizeBefore);
        CarOrder carOrder = new CarOrder("JAMES BOND", 1, 1);
        Integer newOrderId = carOrderService.createOrder(carOrder);
        assertNotNull(newOrderId);
        assertEquals(carOrderSizeBefore, carOrderService.findAllOrder().size() - 1);
    }

    @Test
    void updateOrder() {
        logger.debug("Execute test: carOrderService.update()");
        List<CarOrder> orders = carOrderService.findAllOrder();
        if (orders.size()==0){
            carOrderService.createOrder(new CarOrder("JAMES BOND", 1, 1));
            orders = carOrderService.findAllOrder();
        }
        CarOrder orderSrc = orders.get(0);
        orderSrc.setCustomer(orderSrc.getCustomer()+"_TEST");
        carOrderService.updateOrder(orderSrc);

        CarOrder orderDrt = carOrderService.getOrderById(orderSrc.getOrderId());
        assertEquals(orderSrc.getCustomer(), orderDrt.getCustomer());
    }

    @Test
    void deleteOrder() {
        logger.debug("Execute test: deleteOrder()");
        carOrderService.createOrder(new CarOrder("JAMES BOND", 1, 1));
        List<CarOrder> orders = carOrderService.findAllOrder();
        carOrderService.deleteOrder(orders.get(orders.size()-1).getOrderId());
        assertEquals(orders.size()-1, carOrderService.findAllOrder().size());
    }

    @Test
    void findAllOrder() {
        logger.debug("Execute test: CarOrderService.findAll()");
        assertNotNull(carOrderService);
        assertNotNull(carOrderService.findAllOrder());
    }
}