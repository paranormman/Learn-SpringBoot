package com.vestachrono.springweblearning.springweblearning.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//Using Lombok to define the getters and setters

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Required field in Employee: name")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 10, message = "Name should be in the range [3, 10]")
    private String name;

    @NotBlank(message = "Mail field Should not be blank")
    @Email(message = "Should be a valid email ID")
    private String email;

    @Max(value = 60, message = "Age of Employee can not be greater than 60")
    @Min(value = 18, message = "Age of Employee cannot be less than 18")
    private Integer age;

    @NotNull(message = "Role Field cannot be null")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can either be ADMIN or USER ")
    private String role; // ADMIN or USER in specific

    @PastOrPresent(message = "dateOfJoining can be past or present date not future")
    private LocalDate dateOfJoining;

    @Positive(message = "Salary of Employee should be positive")
    @NotNull(message = "Salary of employee can not be null")
    @Digits(integer = 6, fraction = 2, message = "Salary can be in the form of XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private double salary;

    @AssertTrue(message = "Employees should be active")
    private Boolean isActive;

}