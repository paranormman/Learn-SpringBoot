package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.controller;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.SignUpDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.UserDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.metal.MetalButtonUI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);

    }


}
