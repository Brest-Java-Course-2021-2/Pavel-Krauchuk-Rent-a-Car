package com.epam.brest.rest.dao;

import com.epam.brest.model.dto.CarDto;

import java.util.List;

public interface CarDtoDao {

    List<CarDto> findByPrice();
}
