package com.epam.brest.service;

import com.epam.brest.model.CarOrder;

import java.util.List;

public interface CarOrderService {
    /**
     * * Find all orders.
     *
     * @return orders list.
     */

    List<CarOrder> findAllOrder();

    CarOrder getOrderById(Integer orderId);

    Integer createOrder(CarOrder carOrder);

    Integer updateOrder(CarOrder carOrder);

    Integer deleteOrder(Integer orderId);
}
