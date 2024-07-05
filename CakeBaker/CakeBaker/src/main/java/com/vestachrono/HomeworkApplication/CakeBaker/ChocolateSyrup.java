package com.vestachrono.HomeworkApplication.CakeBaker;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "cake.flavour.syrup", havingValue = "Chocolate")
public class ChocolateSyrup implements Syrup{

    @Override
    public void getSyrupType(){
        System.out.println("This a Chocolate Syrup");
    }

}
