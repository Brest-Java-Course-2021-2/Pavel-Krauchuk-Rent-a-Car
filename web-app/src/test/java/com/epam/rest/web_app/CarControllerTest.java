package com.epam.rest.web_app;

import com.epam.brest.model.Car;
import com.epam.brest.service.CarService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static com.epam.brest.model.constants.CarConstants.CAR_MODEL_SIZE;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Transactional
public class CarControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CarService carService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldReturnCarsPage() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("cars"))
                .andExpect(model().attribute("cars", hasItem(
                        allOf(
                                hasProperty("carId", is(1)),
                                hasProperty("model", is("MERCEDES")),
                                hasProperty("prace", is(BigDecimal.valueOf(19.50)))
                        )
                )))
                .andExpect(model().attribute("cars", hasItem(
                        allOf(
                                hasProperty("carId", is(2)),
                                hasProperty("model", is("PORSCHE")),
                                hasProperty("prace", is(BigDecimal.valueOf(17.30)))
                        )
                )))
                .andExpect(model().attribute("cars", hasItem(
                        allOf(
                                hasProperty("carId", is(3)),
                                hasProperty("model", is("AUDI")),
                                hasProperty("price", isEmptyOrNullString())
                        )
                )));
    }

    @Test
    public void shouldOpenEditCarPageById() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/car/1")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("car"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("car", hasProperty("carId", is(1))))
                .andExpect(model().attribute("car", hasProperty("model", is("KIA"))));
    }

    @Test
    public void shouldUpdateDepartmentAfterEdit() throws Exception {

        String testName = RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/car/1")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("carId", "1")
                                .param("model", testName)
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        Car car = carService.getCarById();
        assertNotNull(car);
        assertEquals(testName, car.getModel());
    }
}
