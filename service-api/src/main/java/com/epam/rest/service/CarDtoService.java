package com.epam.rest.service;

import com.epam.rest.model.dto.CarDto;

import java.util.List;

public interface CarDtoService {

    List<CarDto> findByPrice();
}
