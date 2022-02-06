package com.epam.rest.web_app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Transactional
public class CarControllerTest {
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private CarService carService;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    void shouldReturnCarsPage() throws Exception {
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/cars")
//                ).andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
//                .andExpect(view().name("cars"))
//                .andExpect(model().attribute("cars", hasItem(
//                        allOf(
//                                hasProperty("carId", is(1)),
//                                hasProperty("model", is("MERCEDES")),
//                                hasProperty("prace", is(BigDecimal.valueOf(19.50)))
//                        )
//                )))
//                .andExpect(model().attribute("cars", hasItem(
//                        allOf(
//                                hasProperty("carId", is(2)),
//                                hasProperty("model", is("PORSCHE")),
//                                hasProperty("prace", is(BigDecimal.valueOf(17.30)))
//                        )
//                )))
//                .andExpect(model().attribute("cars", hasItem(
//                        allOf(
//                                hasProperty("carId", is(3)),
//                                hasProperty("model", is("AUDI")),
//                                hasProperty("price", isEmptyOrNullString())
//                        )
//                )));
//    }
}
