package com.vestachrono.springweblearning.springweblearning.controllers;


import com.vestachrono.springweblearning.springweblearning.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "This is my Secret message @#&&!^(&&^!^&*";
//    }

    @GetMapping(path = "/employees/{employeeId}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Manoj", "manoj@gmail.com", 24, LocalDate.of(2024, 1,20),true);
    }

    @GetMapping(path = "/employees")
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi age " + age + " " + sortBy;
    }

}
