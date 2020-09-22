package com.java.weatherreport.weatherforecast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClient {


    @Order(0)
    @Bean(name="restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
