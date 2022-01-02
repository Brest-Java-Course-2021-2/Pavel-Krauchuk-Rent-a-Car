package com.epam.brest.service;

import com.epam.brest.model.dto.CarDto;

import java.util.List;

public interface CarDtoService {

    List<CarDto> exposeTotalPrice();
}
