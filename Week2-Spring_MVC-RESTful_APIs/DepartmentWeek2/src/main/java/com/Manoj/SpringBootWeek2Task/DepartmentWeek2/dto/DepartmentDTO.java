package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.dto;

import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations.CheckIsPrimeValidation;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations.DepartmentRoleValidation;
import com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations.PasswordCheckValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.ImmutableEntityEntry;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotNull(message = "Required Department Title")
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 10, message = "Title should be in range [3, 10]")
    private String title;

    @Email(message = "Enter Valid Mail Address")
    @NotBlank(message = "Email can not be blank")
    private String email;

    @AssertTrue(message = "Department should be Active")
    private Boolean isActive;

    @Past(message = "Date of creation can be past not present or future")
    private LocalDate createdAt;

    @NotBlank(message = "Department Role can not be null")
    @DepartmentRoleValidation
    private String role;

    @CheckIsPrimeValidation
    private Integer checkIsPrime;

    @PasswordCheckValidation
    private String passwordCheck;
}
