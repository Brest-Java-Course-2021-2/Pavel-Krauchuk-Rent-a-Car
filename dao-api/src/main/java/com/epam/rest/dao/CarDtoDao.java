package com.epam.rest.dao;

import com.epam.rest.model.dto.CarDto;

import java.util.List;

public interface CarDtoDao {

    List<CarDto> findByPrice();
}
