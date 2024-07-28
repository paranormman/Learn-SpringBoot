package com.vestaChrono.DataMapping.One_To_One.Mapping.services;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.EmployeeEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
