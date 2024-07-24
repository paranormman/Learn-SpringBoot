package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckIsPrimeValidator implements ConstraintValidator<CheckIsPrimeValidation, Integer> {
    @Override
    public boolean isValid(Integer number, ConstraintValidatorContext constraintValidatorContext) {

        if(number == null) {
            return false;
        }

        for(int i = 2; i < number; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
