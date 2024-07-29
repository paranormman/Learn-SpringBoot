package com.vestaChrono.SchoolMapping.School_MappingHW.services;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.ProfessorEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorEntity createNewProfessor(ProfessorEntity professorEntity) {
        return professorRepository.save(professorEntity);
    }

    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }
}


