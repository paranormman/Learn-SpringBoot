package com.vestaChrono.SchoolMapping.School_MappingHW.services;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.AdmissionRecordEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.entities.StudentEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.AdmissionRecordRepository;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.admissionRecordRepository = admissionRecordRepository;
    }

    public AdmissionRecordEntity createNewAdmissionRecord(AdmissionRecordEntity admissionRecordEntity) {
        return admissionRecordRepository.save(admissionRecordEntity);
    }

    public AdmissionRecordEntity getAdmissionRecordById(Long admissionId) {
        return admissionRecordRepository.findById(admissionId).orElse(null);
    }

    public AdmissionRecordEntity assignAdmissionToStudent(Long admissionId, Long studentId) {
        Optional<AdmissionRecordEntity> admissionRecordEntity = admissionRecordRepository.findById(admissionId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return admissionRecordEntity.flatMap(admissionRecord ->
                studentEntity.map(student -> {
                    admissionRecord.setStudentRecord(student);
                    return admissionRecordRepository.save(admissionRecord);
                })).orElse(null);

    }
}
