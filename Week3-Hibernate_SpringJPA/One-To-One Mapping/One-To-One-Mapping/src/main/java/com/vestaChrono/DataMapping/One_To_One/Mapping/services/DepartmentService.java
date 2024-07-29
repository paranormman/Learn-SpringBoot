package com.vestaChrono.DataMapping.One_To_One.Mapping.services;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.DepartmentEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.EmployeeEntity;
import com.vestaChrono.DataMapping.One_To_One.Mapping.repositories.DepartmentRepository;
import com.vestaChrono.DataMapping.One_To_One.Mapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
//        return employeeEntity.map(employee-> employee.getManagedDepartment()).orElse(null);

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();

        return departmentRepository.findByManager(employeeEntity);

    }
}
