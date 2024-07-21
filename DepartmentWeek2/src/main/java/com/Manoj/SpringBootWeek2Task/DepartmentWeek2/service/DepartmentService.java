package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.service;


import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.dto.DepartmentDTO;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.entities.DepartmentEntity;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).orElse(null);
        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveDepartment = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(toSaveDepartment);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public boolean deleteDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) return false;
        departmentRepository.deleteById(departmentId);
        return true;
    }

}
