package com.epam.brest.service.impl;

import com.epam.brest.model.CarOrder;
import com.epam.brest.rest.dao.CarOrderDao;
import com.epam.brest.service.CarOrderService;
import com.epam.brest.service.exceptions.CarNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarOrderServiceImpl implements CarOrderService {

    private final Logger logger = LogManager.getLogger(CarOrderServiceImpl.class);

    private final CarOrderDao carOrderDao;

    public CarOrderServiceImpl(CarOrderDao carOrderDao) {
        this.carOrderDao = carOrderDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CarOrder getOrderById(Integer orderId) {
        logger.debug("Get order by id = {}", orderId);
        try {
            return this.carOrderDao.getOrderById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new CarNotFoundException(orderId);
        }
    }

    @Override
    @Transactional
    public Integer createOrder(CarOrder carOrder) {
        logger.debug("create order({})", carOrder);
        return this.carOrderDao.createOrder(carOrder);
    }

    @Override
    @Transactional
    public Integer updateOrder(CarOrder carOrder) {
        logger.debug("create order({})", carOrder);
        return this.carOrderDao.updateOrder(carOrder);
    }

    @Override
    @Transactional
    public Integer deleteOrder(Integer orderId) {
        logger.debug("delete order with id = {}", orderId);
        return this.carOrderDao.deleteOrder(orderId);
    }

    @Override
    public List<CarOrder> findAllOrder() {
        logger.trace("order findAll()");
        return carOrderDao.findAllOrder();
    }
}
