package com.vestaChrono.DataMapping.One_To_One.Mapping.services;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.DepartmentEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
