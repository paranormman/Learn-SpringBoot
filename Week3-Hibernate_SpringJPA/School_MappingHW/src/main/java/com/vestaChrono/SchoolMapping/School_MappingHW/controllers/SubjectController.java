package com.vestaChrono.SchoolMapping.School_MappingHW.controllers;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.SubjectEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.services.SubjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public SubjectEntity createNewSubject(@RequestBody SubjectEntity subjectEntity) {
        return subjectService.createNewSubject(subjectEntity);
    }

    @GetMapping(path = "/{subjectId}")
    public SubjectEntity getSubjectById(@PathVariable Long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }


}
