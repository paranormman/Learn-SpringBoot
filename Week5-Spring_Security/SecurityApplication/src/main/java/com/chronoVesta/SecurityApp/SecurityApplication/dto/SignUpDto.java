package com.chronoVesta.SecurityApp.SecurityApplication.dto;

import com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
