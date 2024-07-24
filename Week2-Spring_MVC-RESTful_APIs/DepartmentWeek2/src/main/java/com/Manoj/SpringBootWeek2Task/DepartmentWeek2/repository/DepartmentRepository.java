package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.repository;

import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.dto.DepartmentDTO;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
