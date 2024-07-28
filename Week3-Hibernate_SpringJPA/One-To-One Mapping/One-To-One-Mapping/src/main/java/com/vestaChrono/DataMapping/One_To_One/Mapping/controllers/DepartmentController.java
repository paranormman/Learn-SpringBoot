package com.vestaChrono.DataMapping.One_To_One.Mapping.controllers;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.DepartmentEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.repositories.DepartmentRepository;
import com.vestaChrono.DataMapping.One_To_One.Mapping.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.createNewDepartment(departmentEntity);
    }

}
