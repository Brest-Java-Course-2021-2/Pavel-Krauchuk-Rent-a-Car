package com.epam.brest.rest;

import com.epam.brest.model.Car;
import com.epam.brest.rest.exception.CustomExceptionHandler;
import com.epam.brest.rest.exception.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.model.constants.CarConstants.CAR_MODEL_SIZE;
import static com.epam.brest.rest.CarControllerIT.CARS_ENDPOINT;
import static com.epam.brest.rest.exception.CustomExceptionHandler.CAR_NOT_FOUND;
import static com.epam.brest.rest.exception.CustomExceptionHandler.VALIDATION_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
public class CarControllerTest {

//    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CarControllerTest.class);

    public static final String CAR_ENDPOINT = "/cars";

    @Autowired
    private CarController carController;

    @Autowired
    private CarDtoController carDtoController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    MockMvcCarService carService = new MockMvcCarService();

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    void getCarById(){
    }

    @Test
    public void shouldFindAllDepartments() throws Exception {

        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        Integer id = carService.create(car);

        // when
        List<Car> cars = carService.findAll();

        // then
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void shouldCreateDepartment() throws Exception {
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        Integer id = carService.create(car);
        assertNotNull(id);
    }

    @Test
    public void shouldFindCarById() throws Exception {

        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        Integer id = carService.create(car);

        assertNotNull(id);

        // when
        Optional<Car> optionalCar = carService.findById(id);

        // then
        assertTrue(optionalCar.isPresent());
        assertEquals(optionalCar.get().getModel(), id);
        assertEquals(car.getModel(), optionalCar.get().getModel());
    }

    @Test
    public void shouldUpdateCar() throws Exception {

        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        Integer id = carService.create(car);
        assertNotNull(id);

        Optional<Car> carOptional = carService.findById(id);
        assertTrue(carOptional.isPresent());

        carOptional.get().
                setModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        // when
        int result = carService.update(carOptional.get());

        // then
        assertTrue(1 == result);

        Optional<Car> updatedCarOptional = carService.findById(id);
        assertTrue(updatedCarOptional.isPresent());
        assertEquals(updatedCarOptional.get().getCarId(), id);
        assertEquals(updatedCarOptional.get().getModel(), carOptional.get().getModel());

    }

    @Test
    public void shouldDeleteCar() throws Exception {
        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        Integer id = carService.create(car);

        List<Car> cars = carService.findAll();
        assertNotNull(cars);

        // when
        int result = carService.delete(id);

        // then
        assertTrue(1 == result);

        List<Car> currentCars = carService.findAll();
        assertNotNull(currentCars);

        assertTrue(cars.size() - 1 == currentCars.size());
    }

    @Test
    public void shouldReturnCarNotFoundError() throws Exception {

//        LOGGER.debug("shouldReturnCarNotFoundError()");
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(CARS_ENDPOINT + "/999999")
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isNotFound())
                        .andReturn().getResponse();
        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), CAR_NOT_FOUND);
    }

    @Test
    public void shouldFailOnCreateCarWithDuplicateModel() throws Exception {
        Car car1 = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        Integer id = carService.create(car1);
        assertNotNull(id);

        Car car2 = new Car(car1.getModel());

        MockHttpServletResponse response =
                mockMvc.perform(post(CARS_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(car2))
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isUnprocessableEntity())
                        .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), VALIDATION_ERROR);
    }

    class MockMvcCarService {

        public List<Car> findAll() throws Exception {
//            LOGGER.debug("findAll()");
            MockHttpServletResponse response = mockMvc.perform(get(CARS_ENDPOINT)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Car>>() {
            });
        }

        public Optional<Car> findById(Integer id) throws Exception {

//            LOGGER.debug("findById({})", id);
            MockHttpServletResponse response = mockMvc.perform(get(CARS_ENDPOINT + "/" + id)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Car.class));
        }

        public Integer create(Car car) throws Exception {

//            LOGGER.debug("create({})", car);
            String json = objectMapper.writeValueAsString(car);
            MockHttpServletResponse response =
                    mockMvc.perform(post(CARS_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        private int update(Car car) throws Exception {

//            LOGGER.debug("update({})", car);
            MockHttpServletResponse response =
                    mockMvc.perform(put(CARS_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(car))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        private int delete(Integer carId) throws Exception {

//            LOGGER.debug("delete(id:{})", carId);
            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(new StringBuilder(CARS_ENDPOINT).append("/")
                                            .append(carId).toString())
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();

            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}