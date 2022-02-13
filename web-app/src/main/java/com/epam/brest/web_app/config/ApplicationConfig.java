package com.epam.brest.web_app.config;


import com.epam.brest.service.CarDtoService;
import com.epam.brest.service.CarDtoServiceRest;
import com.epam.brest.service.CarService;
import com.epam.brest.service.CarServiceRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class ApplicationConfig {

    @Value("${rest.server.protocol}")
    private String protocol;
    @Value("${rest.server.host}")
    private String host;
    @Value("${rest.server.port}")
    private Integer port;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean
    CarDtoService carDtoService() {
        String url = String.format("%s://%s:%d/car-dtos", protocol, host, port);
        return new CarDtoServiceRest(url, restTemplate());
    }

    ;

    @Bean
    CarService carService() {
        String url = String.format("%s://%s:%d/cars", protocol, host, port);
        return new CarServiceRest(url, restTemplate());
    }
}
