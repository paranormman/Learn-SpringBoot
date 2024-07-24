package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DepartmentRoleValidator.class})
public @interface DepartmentRoleValidation {
    String message() default "Department Role Should be Either Manager, Admin or HR";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
