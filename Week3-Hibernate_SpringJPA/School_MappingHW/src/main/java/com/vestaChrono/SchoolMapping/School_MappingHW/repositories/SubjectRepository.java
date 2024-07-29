package com.vestaChrono.SchoolMapping.School_MappingHW.repositories;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
