package com.vestachrono.manoj.week1intoduction.Introduction.To.SpringBoot;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

//    @Autowired              //Field injection using Autowired annotation
    final private DB db;

    public DBService(DB db){    //constructor injection we need not use the "Autowired" annotation
        this.db = db;
    }

    String getData(){
        return db.getData();
    }
}
