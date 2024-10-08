package com.vestaChrono.SchoolMapping.School_MappingHW.controllers;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.ProfessorEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.entities.SubjectEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorEntity createNewProfessor(@RequestBody ProfessorEntity professorEntity) {
        return professorService.createNewProfessor(professorEntity);
    }

    @GetMapping(path = "/{professorId}")
    public ProfessorEntity getProfessorById(@PathVariable Long professorId) {
        return professorService.getProfessorById(professorId);
    }

    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public ProfessorEntity addSubjectToProfessor(@PathVariable Long professorId,
                                                 @PathVariable Long subjectId) {
        return professorService.addSubjectToProfessor(professorId, subjectId);
    }

    @PutMapping(path = "/{professorId}/students/{studentId}")
    public ProfessorEntity addProfessorToStudent(@PathVariable Long professorId,
                                                 @PathVariable Long studentId) {
        return professorService.addProfessorToStudent(professorId, studentId);
    }

}
