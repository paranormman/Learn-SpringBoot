package com.vestachrono.HomeworkApplication.CakeBaker;


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

    public void bakeCake(){
        frosting.getFrostingType();
        syrup.getSyrupType();
    }
}
