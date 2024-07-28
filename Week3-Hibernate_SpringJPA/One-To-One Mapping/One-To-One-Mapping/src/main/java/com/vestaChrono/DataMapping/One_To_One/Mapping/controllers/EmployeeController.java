package com.vestaChrono.DataMapping.One_To_One.Mapping.controllers;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.EmployeeEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.repositories.EmployeeRepository;
import com.vestaChrono.DataMapping.One_To_One.Mapping.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return employeeService.createNewEmployee(employeeEntity);
    }

}
