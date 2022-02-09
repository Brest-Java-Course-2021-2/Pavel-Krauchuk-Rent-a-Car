package com.epam.brest.service.impl;

import com.epam.brest.rest.dao.CarDtoDao;
import com.epam.brest.model.dto.CarDto;
import com.epam.brest.service.CarDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarDtoServiceImpl implements CarDtoService {

    private final Logger logger = LogManager.getLogger(CarDtoServiceImpl.class);

    private final CarDtoDao carDtoDao;

    public CarDtoServiceImpl(CarDtoDao carDtoDao) {
        this.carDtoDao = carDtoDao;
    }

    @Override
    public List<CarDto> findByPrice() {
        logger.debug("findByPrice({})");
        return this.carDtoDao.findByPrice();
    }
}
