package com.epam.rest.rest;

import com.epam.rest.CarDtoController;
import com.epam.rest.model.dto.CarDto;
import com.epam.rest.service.CarDtoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class CarDtoControllerTest {

    @InjectMocks
    private CarDtoController carDtoController;

    @Mock
    private CarDtoService carDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carDtoController)
                .build();
    }

    @AfterEach
    public void end() {
        Mockito.verifyNoMoreInteractions(carDtoService);
    }

    @Test
    public void shouldFindAllWithAvgSalary() throws Exception {

        Mockito.when(carDtoService.findByPrice()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/department_dtos")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is("d0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].avgSalary", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is("d1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].avgSalary", Matchers.is(101)))
        ;

        Mockito.verify(carDtoService).findByPrice();
    }

    private CarDto create(int index) {
        CarDto carDto = new CarDto();
        carDto.setCarId(index);
        carDto.setModel("d" + index);
        carDto.setPrice(BigDecimal.valueOf(100 + index));
        return carDto;
    }
}