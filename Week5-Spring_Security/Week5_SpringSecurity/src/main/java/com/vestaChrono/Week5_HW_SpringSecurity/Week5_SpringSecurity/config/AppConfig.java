package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper getmodelMapper() {
        return new ModelMapper();
    }

}
