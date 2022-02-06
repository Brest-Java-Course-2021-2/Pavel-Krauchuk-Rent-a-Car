package com.epam.brest.service.impl;

import com.epam.rest.dao.CarDtoDao;
import com.epam.rest.model.dto.CarDto;
import com.epam.rest.service.CarDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarDtoServiceImpl implements CarDtoService {

    private final CarDtoDao carDtoDao;

    public CarDtoServiceImpl(CarDtoDao carDtoDao) {
        this.carDtoDao = carDtoDao;
    }

    @Override
    public List<CarDto> findByPrice() {
        return this.carDtoDao.findByPrice();
    }
}
