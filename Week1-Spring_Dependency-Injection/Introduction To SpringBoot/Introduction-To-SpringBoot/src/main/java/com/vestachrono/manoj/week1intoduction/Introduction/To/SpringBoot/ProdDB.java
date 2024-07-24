package com.vestachrono.manoj.week1intoduction.Introduction.To.SpringBoot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary        //used to define which bean should be scanned first
@ConditionalOnProperty(value = "deploy.env", havingValue = "production")
public class ProdDB implements DB{

    public String getData(){
        return "Prod Data";
    }
}
