package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.controller;


import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.dto.DepartmentDTO;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.entities.DepartmentEntity;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentDTO getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments(@RequestParam(required = false) Integer size
            ,@RequestParam(required = false) String sortBy) {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public DepartmentDTO createNewDepartment(@RequestBody DepartmentDTO inputDepartment) {
        return departmentService.createNewDepartment(inputDepartment);
    }

    @DeleteMapping(path = "/{departmentId}")
    public boolean deleteDepartmentId(@PathVariable Long departmentId) {
        return departmentService.deleteDepartmentId(departmentId);
    }


}
