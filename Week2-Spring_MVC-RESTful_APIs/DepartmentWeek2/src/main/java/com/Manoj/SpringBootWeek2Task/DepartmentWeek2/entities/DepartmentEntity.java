package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String email;
    private Boolean isActive;
    private LocalDate createdAt;
    private String role;
    private Integer checkIsPrime;
    private String passwordCheck;
}
