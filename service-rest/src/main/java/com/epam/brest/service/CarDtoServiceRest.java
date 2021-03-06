package com.epam.brest.service;

import com.epam.brest.model.dto.CarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CarDtoServiceRest implements CarDtoService{

    private String url;

    private RestTemplate restTemplate;

    public CarDtoServiceRest() {
        // empty default constructor
    }

    public CarDtoServiceRest(String url, RestTemplate restTemplate) {
        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CarDto> findByPrice() {
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<CarDto>) responseEntity.getBody();
    }
}
