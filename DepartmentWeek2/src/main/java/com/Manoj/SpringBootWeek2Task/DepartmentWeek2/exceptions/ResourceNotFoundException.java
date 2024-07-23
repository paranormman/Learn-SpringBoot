package com.Manoj.SpringBootWeek2Task.DepartmentWeek2.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
