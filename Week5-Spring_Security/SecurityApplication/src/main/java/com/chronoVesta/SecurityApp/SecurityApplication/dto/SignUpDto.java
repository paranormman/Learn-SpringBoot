package com.chronoVesta.SecurityApp.SecurityApplication.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
}
