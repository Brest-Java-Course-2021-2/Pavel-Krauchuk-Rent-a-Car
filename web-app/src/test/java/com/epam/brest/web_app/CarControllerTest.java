package com.epam.brest.web_app;

import com.epam.brest.model.Car;
import com.epam.brest.model.dto.CarDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;

import static com.epam.brest.model.constants.CarConstants.CAR_MODEL_SIZE;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@Disabled
public class CarControllerTest {

    private static final String CAR_DTOS_URL = "http://localhost:8088/car-dtos";
    private static final String CARS_URL = "http://localhost:8088/cars";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldReturnCarsPage() throws Exception {

        CarDto d1 = createCarDto(1, "KIA", BigDecimal.valueOf(19.50));
        CarDto d2 = createCarDto(2, "LADA", BigDecimal.valueOf(14.10));
        CarDto d3 = createCarDto(3, "MODEL T", BigDecimal.valueOf(90.50));
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CAR_DTOS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(d1, d2, d3)))
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("cars"))
                .andExpect(model().attribute("cars", hasItem(
                        allOf(
                                hasProperty("carId", is(d1.getCarId())),
                                hasProperty("model", is(d1.getModel())),
                                hasProperty("price", is(d1.getPrice()))
                        )
                )))
//                .andExpect(model().attribute("departments", hasItem(
//                        allOf(
//                                hasProperty("departmentId", is(d2.getDepartmentId())),
//                                hasProperty("departmentName", is(d2.getDepartmentName())),
//                                hasProperty("avgSalary", is(d2.getAvgSalary()))
//                        )
//                )))
//                .andExpect(model().attribute("departments", hasItem(
//                        allOf(
//                                hasProperty("departmentId", is(d3.getDepartmentId())),
//                                hasProperty("departmentName", is(d3.getDepartmentName())),
//                                hasProperty("avgSalary", isEmptyOrNullString())
//                        )
//                )))
        ;

        mockServer.verify();
    }

    @Test
    void shouldAddCar() throws Exception {
        // WHEN
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );

        Car car = new Car("MODEL T");

        // THEN
        //Integer newDepartmentId = departmentService.create(department);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/department")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("model", car.getModel())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));


        // VERIFY
        mockServer.verify();
    }

    @Test
    void shouldFailAddCarOnEmptyName() throws Exception {
        // WHEN
        Car car = new Car("MODEL T");

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/department")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("model", car.getModel())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "model"
                        )
                );
    }

    @Test
    public void shouldOpenEditCarPageById() throws Exception {
        Car d = createCar(1, "MODEL T");
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + d.getCarId())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(d))
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/car/1")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("car"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("car", hasProperty("carId", is(1))))
                .andExpect(model().attribute("car", hasProperty("model", is("MODEL T"))));
    }

    @Test
    public void shouldUpdateCarAfterEdit() throws Exception {

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        String testName = RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/department/1")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("carId", "1")
                                .param("model", testName)
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        mockServer.verify();
    }

    @Test
    public void shouldDeleteCar() throws Exception {

        int id = 3;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/car/3/delete")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        mockServer.verify();
    }

    private CarDto createCarDto(int id, String name, BigDecimal price) {
        CarDto carDto = new CarDto();
        carDto.setCarId(id);
        carDto.setModel(name);
        carDto.setPrice(price);
        return carDto;
    }

    private Car createCar(int id, String model) {
        Car car = new Car();
        car.setCarId(id);
        car.setModel(model);
        return car;
    }
}