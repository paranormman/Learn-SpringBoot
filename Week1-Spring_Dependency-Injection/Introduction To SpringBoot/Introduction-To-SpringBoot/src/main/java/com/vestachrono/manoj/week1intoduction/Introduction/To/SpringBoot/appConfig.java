package com.vestachrono.manoj.week1intoduction.Introduction.To.SpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class appConfig {

    @Bean
//    @Scope("prototype")         //for a prototype scope individual beans will be created for each object initiated
    @Scope("singleton")         //for a singleton scope beans will be created only once for any number of objects initiated (DEFAULT)
    Apple getApple(){
        return new Apple();
    }
}
