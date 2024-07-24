package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;

public class PasswordCheckValidator implements ConstraintValidator<PasswordCheckValidation, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null) {
            return false;
        }
        else {
            boolean check = isValidPassword(password);
            return check;
        }
    }

    private boolean isValidPassword(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChars = false;
        String specialChars = "!@#$%^&*()_+{}[]:;',./";


//        check for minimum length
        if(password.length() <= 10){
            return false;
        }

        for(char c:password.toCharArray()){

            if(Character.isUpperCase(c)){
                hasUpperCase = true;
            }
            else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            else if (specialChars.indexOf(c) >= 0) {
                hasSpecialChars = true;
            }

            if (hasUpperCase && hasLowerCase && hasSpecialChars){
                return true;

            }

        }
        return hasUpperCase && hasLowerCase && hasSpecialChars;


    }
}
