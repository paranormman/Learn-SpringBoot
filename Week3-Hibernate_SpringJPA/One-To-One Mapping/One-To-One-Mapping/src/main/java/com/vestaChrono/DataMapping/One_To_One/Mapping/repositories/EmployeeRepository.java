package com.vestaChrono.DataMapping.One_To_One.Mapping.repositories;

import com.vestaChrono.DataMapping.One_To_One.Mapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
