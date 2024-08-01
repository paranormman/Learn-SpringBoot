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

    @PutMapping(path = "/{studentId}/subjects/{subjectId}")
    public StudentEntity addStudentToSubject(@PathVariable Long studentId,
                                             @PathVariable Long subjectId) {
        return studentService.addStudentToSubject(studentId, subjectId);
    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
    }

}
