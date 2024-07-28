package com.vestaChrono.DataMapping.One_To_One.Mapping.repositories;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
