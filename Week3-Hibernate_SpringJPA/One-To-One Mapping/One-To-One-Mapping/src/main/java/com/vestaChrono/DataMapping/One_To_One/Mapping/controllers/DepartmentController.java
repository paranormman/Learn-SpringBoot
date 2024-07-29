package com.vestaChrono.DataMapping.One_To_One.Mapping.controllers;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.DepartmentEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId) {
        return departmentService.assignManagerToDepartment(departmentId, employeeId);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId) {
        return departmentService.getAssignedDepartmentOfManager(employeeId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId) {
        return departmentService.assignWorkerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancers/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId,
                                                         @PathVariable Long employeeId) {
        return departmentService.assignFreelancerToDepartment(departmentId, employeeId);
    }
}
