package com.epam.brest.service.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class CarNotFoundException extends EmptyResultDataAccessException {
    public CarNotFoundException(Integer id) {
        super("Car not found for id: " + id, 1);
    }
}
