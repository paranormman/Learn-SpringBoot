package com.vestaChrono.DataMapping.One_To_One.Mapping.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "departments")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "department_name")
    private EmployeeEntity manager;

}
