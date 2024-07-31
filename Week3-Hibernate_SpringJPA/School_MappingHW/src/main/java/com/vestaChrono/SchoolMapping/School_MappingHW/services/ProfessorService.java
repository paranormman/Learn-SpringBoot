package com.vestaChrono.SchoolMapping.School_MappingHW.services;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.ProfessorEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.entities.StudentEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.entities.SubjectEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.ProfessorRepository;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.StudentRepository;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public ProfessorService(ProfessorRepository professorRepository, SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public ProfessorEntity createNewProfessor(ProfessorEntity professorEntity) {
        return professorRepository.save(professorEntity);
    }

    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }

    public ProfessorEntity addSubjectToProfessor(Long professorId, Long subjectId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);

        return professorEntity.flatMap(professor ->
                subjectEntity.map(subject -> {
                    subject.setProfessor(professor);
                    subjectRepository.save(subject);

                    professor.getSubjects().add(subject);
                    return professor;
                })).orElse(null);

    }

    public ProfessorEntity addProfessorToStudent(Long professorId, Long studentId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);


        return professorEntity.flatMap(professor ->
                studentEntity.map(student->{
                    student.getProfessors().add(professor);

                    professor.getStudents().add(student);
                    professorRepository.save(professor);
                    return professor;
                })
        ).orElse(null);
    }
}


