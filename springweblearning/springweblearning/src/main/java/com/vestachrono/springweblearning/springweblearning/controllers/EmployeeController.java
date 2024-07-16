package com.vestachrono.springweblearning.springweblearning.controllers;


import com.vestachrono.springweblearning.springweblearning.dto.EmployeeDTO;
import com.vestachrono.springweblearning.springweblearning.entities.EmployeeEntity;
import com.vestachrono.springweblearning.springweblearning.repositories.EmployeeRepository;
import com.vestachrono.springweblearning.springweblearning.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "This is my Secret message @#&&!^(&&^!^&*";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeByID(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeByID(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById() {
        return  "Hello from PUT";
    }

    @PatchMapping
    public String updateEmployee() {
        return  "Hello from PUT";
    }

}
