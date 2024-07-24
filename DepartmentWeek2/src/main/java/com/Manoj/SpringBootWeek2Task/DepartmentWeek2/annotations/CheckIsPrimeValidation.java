package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckIsPrimeValidator.class})
public @interface CheckIsPrimeValidation {

    String message() default "Check if Entered Value is a Prime Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
