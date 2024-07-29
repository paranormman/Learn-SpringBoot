package com.vestaChrono.SchoolMapping.School_MappingHW.repositories;

import com.vestaChrono.SchoolMapping.School_MappingHW.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
