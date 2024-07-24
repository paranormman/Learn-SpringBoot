package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.service;


import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.dto.DepartmentDTO;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.entities.DepartmentEntity;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.exceptions.ResourceNotFoundException;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));

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
        isExistsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDTO updateDepartmentById(Long departmentId, DepartmentDTO departmentDTO) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public void isExistsByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists) throw new ResourceNotFoundException("Department not found with ID "+ departmentId);
    }

    public DepartmentDTO updatePartialDepartmentById(Long departmentId, Map<String, Object> updates) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updates.forEach((field, value) -> {
            Field fieldToUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated, departmentEntity, value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }
}
