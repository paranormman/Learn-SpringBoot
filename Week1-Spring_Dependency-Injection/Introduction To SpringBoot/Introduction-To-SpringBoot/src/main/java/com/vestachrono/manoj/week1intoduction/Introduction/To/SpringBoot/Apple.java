package com.vestachrono.manoj.week1intoduction.Introduction.To.SpringBoot;

//import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//@Component
public class Apple {
    void eatApple(){
        System.out.println("I am eating an Apple");
    }

    @PostConstruct
    void callThisBeforeAppleIsUsed(){                   //This will be called before creating an object of apple class
        System.out.println("Creating Apple before use");
    }

    @PreDestroy
    void callThisBeforeDestroy(){
        System.out.println("The Bean is Destroyed");
    }
}
