package com.epam.brest.rest;

import com.epam.brest.model.dto.CarDto;
import com.epam.brest.service.CarDtoService;
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
    public void shouldFindByPrice() throws Exception {

        Mockito.when(carDtoService.findByPrice()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/car_dtos")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("d0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", Matchers.is("d1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", Matchers.is(11)))
        ;

        Mockito.verify(carDtoService).findByPrice();
    }

    private CarDto create(int index) {
        CarDto carDto = new CarDto();
        carDto.setCarId(index);
        carDto.setModel("d" + index);
        carDto.setPrice(BigDecimal.valueOf(10 + index));
        return carDto;
    }
}
