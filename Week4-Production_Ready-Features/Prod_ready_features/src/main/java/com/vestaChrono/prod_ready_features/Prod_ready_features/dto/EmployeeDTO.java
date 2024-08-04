package com.vestaChrono.prod_ready_features.Prod_ready_features.dto;

import lombok.*;

import java.time.LocalDate;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role; // ADMIN or USER in specific

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;

}