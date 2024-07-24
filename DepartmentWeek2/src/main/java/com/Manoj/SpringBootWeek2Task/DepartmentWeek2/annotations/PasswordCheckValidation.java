package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordCheckValidator.class})
public @interface PasswordCheckValidation {

    String message() default "Password Should contain a uppercase, lowercase, special character and should be of size 10";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
