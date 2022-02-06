package com.epam.rest.dao;

import com.epam.rest.model.CarOrder;

import java.util.List;

public interface CarOrderDao {

    List<CarOrder> findAllOrder();

    Integer createOrder(CarOrder carOrder);

    Integer updateOrder(CarOrder carOrder);

    Integer deleteOrder (Integer carOrder);
}
