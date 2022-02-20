package com.epam.brest.rest.dao;

import com.epam.brest.model.CarOrder;

import java.util.List;

public interface CarOrderDtoDao {

    List<CarOrder>findAll();
}
