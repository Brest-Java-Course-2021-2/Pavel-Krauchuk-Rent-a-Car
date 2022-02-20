package com.epam.brest.rest.dao;

import com.epam.brest.model.CarOrder;

import java.util.List;

public interface CarOrderDao {

    List<CarOrder> findAllOrder();

    CarOrder getOrderById (Integer orderId);

    Integer createOrder(CarOrder carOrder);

    Integer updateOrder(CarOrder carOrder);

    Integer deleteOrder (Integer orderId);
}
