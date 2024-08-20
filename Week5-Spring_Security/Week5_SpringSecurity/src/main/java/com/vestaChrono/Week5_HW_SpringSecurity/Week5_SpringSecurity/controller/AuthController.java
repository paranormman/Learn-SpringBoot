package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.controller;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.LoginDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.SignUpDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.UserDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.LoginAuthService;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.SessionService;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final LoginAuthService loginAuthService;
    private final SessionService sessionService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto,
                                        HttpServletRequest request, HttpServletResponse response) {
        String token = loginAuthService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }


}
