package com.vestaChrono.SchoolMapping.School_MappingHW.services;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.SubjectEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectEntity createNewSubject(SubjectEntity subjectEntity) {
        return subjectRepository.save(subjectEntity);
    }

    public SubjectEntity getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }
}
