package com.vestachrono.HomeworkApplication.CakeBaker;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    @Autowired
    private final Frosting frosting;        //This is an object of Frosting
    @Autowired
    private final Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup){
        this.frosting = frosting;
        this.syrup = syrup;

    }

    @PostConstruct
    void printThisBeforeCake(){
        System.out.println("Choose your cake topping type");
    }

    public void bakeCake(){
        frosting.getFrostingType();
        syrup.getSyrupType();
    }
}
