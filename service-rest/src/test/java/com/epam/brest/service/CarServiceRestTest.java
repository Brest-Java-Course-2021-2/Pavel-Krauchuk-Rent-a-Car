package com.epam.brest.service;

import com.epam.brest.model.Car;
import com.epam.brest.service.config.ServiceRestTestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static com.epam.brest.model.constants.CarConstants.CAR_MODEL_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({ServiceRestTestConfig.class})
class CarServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceRestTest.class);

    public static final String CARS_URL = "http://localhost:8088/cars";

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    CarServiceRest carService;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        carService = new CarServiceRest(CARS_URL, restTemplate);
    }

    @Test
    void findAll() throws URISyntaxException, JsonProcessingException {
        LOGGER.debug("shouldFindAllCars()");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );

        // when
        List<Car> cars = carService.findAll();

        // then
        mockServer.verify();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    void getCarById() throws URISyntaxException, JsonProcessingException {

        // given
        Integer id = 1;
        Car car = new Car()
                .setCarId(id)
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(car))
                );

        // when
        Car resultCar = carService.getCarById(id);

        // then
        mockServer.verify();
        assertNotNull(resultCar);
        assertEquals(resultCar.getCarId(), id);
        assertEquals(resultCar.getModel(), car.getModel());
    }

    @Test
    void create() throws URISyntaxException, JsonProcessingException {
        LOGGER.debug("shouldCreateCar()");
        // given
        Car car = new Car()
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        Integer id = carService.create(car);

        // then
        mockServer.verify();
        assertNotNull(id);
    }

    @Test
    void update() throws URISyntaxException, JsonProcessingException {
        // given
        Integer id = 1;
        Car car = new Car()
                .setCarId(id)
                .setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(car))
                );

        // when
        int result = carService.update(car);
        Car updatedCar = carService.getCarById(id);

        // then
        mockServer.verify();
        assertTrue(1 == result);

        assertNotNull(updatedCar);
        assertEquals(updatedCar.getCarId(), id);
        assertEquals(updatedCar.getModel(), car.getModel());

    }

    @Test
    void delete() throws URISyntaxException, JsonProcessingException {
        // given
        Integer id = 1;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        int result = carService.delete(id);

        // then
        mockServer.verify();
        assertTrue(1 == result);
    }

    private Car create(int index) {
        Car car = new Car();
        car.setCarId(index);
        car.setModel("d" + index);
        return car;
    }
}