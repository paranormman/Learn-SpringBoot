package com.vestaChrono.SchoolMapping.School_MappingHW.controllers;


import com.vestaChrono.SchoolMapping.School_MappingHW.entities.StudentEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentEntity createNewStudent(@RequestBody StudentEntity studentEntity) {
        return studentService.createNewStudent(studentEntity);
    }

    @GetMapping(path = "/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

}
