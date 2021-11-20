package com.epam.brest.dao;

import com.epam.brest.model.CarOrder;

import java.util.List;

public interface CarOrderDao {

    List<CarOrder> findAll();

    Integer create(CarOrder carOrder);

    Integer update(CarOrder carOrder);

    Integer delete (CarOrder carOrder);
}
