package com.vestaChrono.SchoolMapping.School_MappingHW.services;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.AdmissionRecordEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.repositories.AdmissionRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
    }

    public AdmissionRecordEntity createNewAdmissionRecord(AdmissionRecordEntity admissionRecordEntity) {
        return admissionRecordRepository.save(admissionRecordEntity);
    }

    public AdmissionRecordEntity getAdmissionRecordById(Long admissionId) {
        return admissionRecordRepository.findById(admissionId).orElse(null);
    }

}
