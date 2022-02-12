package com.epam.brest.service;

import com.epam.brest.model.Car;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceRest implements CarService{

    private String url;

    private RestTemplate restTemplate;

    public CarServiceRest() {
    }

    public CarServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Car> findAll() {
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Car>) responseEntity.getBody();
    }

    @Override
    public Car getCarById(Integer carId) {
        ResponseEntity<Car> responseEntity =
                restTemplate.getForEntity(url + "/" + carId, Car.class);
        return responseEntity.getBody();
    }

    @Override
    public Integer create(Car car) {
        ResponseEntity responseEntity = restTemplate.postForEntity(url, car, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer update(Car car) {
        // restTemplate.put(url, department);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Car> entity = new HttpEntity<>(car, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer delete(Integer carId) {
        //restTemplate.delete(url + "/" + departmentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Car> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + carId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }
}
