package com.VestaChrono.TestApplication.service.impl;

import com.VestaChrono.TestApplication.dto.EmployeeDto;
import com.VestaChrono.TestApplication.entity.Employee;
import com.VestaChrono.TestApplication.exception.ResourceNotFoundException;
import com.VestaChrono.TestApplication.repository.EmployeeRepository;
import com.VestaChrono.TestApplication.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with Id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });
        log.info("Successfully fetched Employee with Id: {}", id);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        log.info("Create new employee with email {}", employeeDto.getEmail());
        List<Employee> existingEmployees = employeeRepository.findByEmail(employeeDto.getEmail());
//        check if the employee with email is already created
        if (!existingEmployees.isEmpty()){
            log.error("Employee already exists with email: {}", employeeDto.getEmail());
            throw new RuntimeException("Employee already exists with email: {}" + employeeDto.getEmail());
        }

//        convert employeeDto to entity to create a new employee and save in repo
        Employee newEmployee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        log.info("Successfully created employee with Id: {}", employeeDto.getId());
//        return the employee by mapping into dto
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating employee with Id: {}", id);
//        check if the employee with id exists in the repository
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with Id: {}",id);
                    return new ResourceNotFoundException("employee not found with Id: " + id);
                });
//        check if the correct user is updating the entry
        if (!employee.getEmail().equals(employeeDto.getEmail())) {
            log.error("Attempted to update email for employee with id: {}", id);
            throw new RuntimeException("The email of the employee can not be updated");
        }
//        update the details and map it to employee
        employeeDto.setId(null);
        modelMapper.map(employeeDto, employee);
//        save the updated employee to repository
        Employee savedEmployee = employeeRepository.save(employee);
//        return updated employee as employeeDto
        log.info("Successfully updated employee with id: {}", id);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with Id: {}", id);
//        check if the employeeId exists
        boolean isExists = employeeRepository.existsById(id);
        if (!isExists) {
            log.error("Employee not found with Id: {}", id);
            throw new ResourceNotFoundException("Employee not found with Id: "+id);
        }
//        delete employee from repository
        employeeRepository.deleteById(id);
        log.info("Successfully deleted Employee with Id: {}", id);
    }
}
