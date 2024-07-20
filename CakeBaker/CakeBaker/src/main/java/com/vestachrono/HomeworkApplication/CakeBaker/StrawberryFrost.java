package com.vestachrono.HomeworkApplication.CakeBaker;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "cake.flavour.frost", havingValue = "Strawberry")
public class StrawberryFrost implements Frosting{

    @Override
    public void getFrostingType(){
        System.out.println("This is a Strawberry Frosting");
    }
}
