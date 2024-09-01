package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
