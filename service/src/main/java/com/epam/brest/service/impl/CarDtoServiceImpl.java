package src.main.java.com.epam.brest.service.impl;

import com.epam.brest.dao.CarDtoDao;
import com.epam.brest.service.CarDtoService;
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
    public List<com.epam.brest.model.dto.CarDto> exposeTotalPrice() {
        return null;
    }
}
