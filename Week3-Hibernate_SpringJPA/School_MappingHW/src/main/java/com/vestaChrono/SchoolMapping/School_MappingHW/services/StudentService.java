package com.vestaChrono.SchoolMapping.School_MappingHW.services;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.StudentEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity createNewStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

}
