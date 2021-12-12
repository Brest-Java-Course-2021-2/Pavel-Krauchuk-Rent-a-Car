package com.epam.brest.service.imp;


import com.epam.brest.dao.CarDtoDao;
import com.epam.brest.model.dto.CarDto;
import com.epam.brest.service.CarDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarDtoServiceImpl implements CarDtoService {

    private final CarDtoDao carDtoDao;

    public CarDtoServiceImpl(CarDtoDao carDtoDao){
        this.carDtoDao = carDtoDao;
    }

    @Override
    public List<CarDto> findAllWithPrice() {
        return carDtoDao.findAllWithAvgPrice();
    }
}
