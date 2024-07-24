package com.vestachrono.HomeworkApplication.CakeBaker;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "cake.flavour.frost", havingValue = "Chocolate")
public class ChocolateFrost implements Frosting {

    @Override
    public void getFrostingType(){
        System.out.println("This is a Chocolate Frost");
    }
}
