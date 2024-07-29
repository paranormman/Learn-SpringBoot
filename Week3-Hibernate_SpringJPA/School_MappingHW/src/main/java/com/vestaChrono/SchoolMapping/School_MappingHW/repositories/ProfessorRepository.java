package com.vestaChrono.SchoolMapping.School_MappingHW.repositories;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
