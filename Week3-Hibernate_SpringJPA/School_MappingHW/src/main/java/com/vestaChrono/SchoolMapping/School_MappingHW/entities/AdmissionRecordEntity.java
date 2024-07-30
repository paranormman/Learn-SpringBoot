package com.vestaChrono.SchoolMapping.School_MappingHW.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Admissions")
public class AdmissionRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String student;

    private Integer fees;

    @OneToOne
    @JoinColumn(name = "Student_Admitted")
    private StudentEntity studentRecord;

}
