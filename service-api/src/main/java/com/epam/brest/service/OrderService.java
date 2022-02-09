package com.epam.brest.service;

import com.epam.brest.model.CarOrder;
import com.epam.brest.model.OrderStatus;

import java.util.List;

public interface OrderService {
    /**
     * * Find all orders.
     *
     * @return orders list.
     */

    List<CarOrder> findAll();

    /**
     * Find order by status.
     * @param orderStatus order status.
     * @return order list.
     */

    CarOrder getCarOrderByStatus (OrderStatus orderStatus);

    /**
     * Changing order status.
     * @param carOrderId order Id
     * @return orderStatus.
     */

    CarOrder setCatOrderStatus (CarOrder carOrderId);
}
