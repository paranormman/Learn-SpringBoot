package com.VestaChrono.TestApplication.service;

import com.VestaChrono.TestApplication.dto.EmployeeDto;

import java.util.Optional;

public interface EmployeeService {
    EmployeeDto getEmployeeById(Long id);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
