package com.vestachrono.manoj.week1intoduction.Introduction.To.SpringBoot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "deploy.env", havingValue = "development")
public class DevDB implements DB{

    public String getData(){
        return "Dev Data";
    }
}
