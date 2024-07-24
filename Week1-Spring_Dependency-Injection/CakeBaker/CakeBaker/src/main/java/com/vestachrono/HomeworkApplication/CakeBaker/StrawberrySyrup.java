package com.vestachrono.HomeworkApplication.CakeBaker;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "cake.flavour.syrup", havingValue = "Strawberry")
public class StrawberrySyrup implements Syrup{

    @Override
    public void getSyrupType(){
        System.out.println("This is a Strawberry Syrup");
    }
}
