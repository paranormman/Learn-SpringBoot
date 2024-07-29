package com.vestaChrono.SchoolMapping.School_MappingHW.controllers;


import com.vestaChrono.SchoolMapping.School_MappingHW.entities.AdmissionRecordEntity;
import com.vestaChrono.SchoolMapping.School_MappingHW.services.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admissions")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @PostMapping
    public AdmissionRecordEntity createNewAdmissionRecord(@RequestBody AdmissionRecordEntity admissionRecordEntity) {
        return  admissionRecordService.createNewAdmissionRecord(admissionRecordEntity);
    }

    @GetMapping(path = "/{admissionId}")
    public AdmissionRecordEntity getAdmissionRecordById(@PathVariable Long admissionId) {
        return admissionRecordService.getAdmissionRecordById(admissionId);
    }

}
