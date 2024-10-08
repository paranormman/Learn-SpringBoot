package com.chronoVesta.SecurityApp.SecurityApplication.controller;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.LoginDto;
import com.chronoVesta.SecurityApp.SecurityApplication.dto.LoginResponseDto;
import com.chronoVesta.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.chronoVesta.SecurityApp.SecurityApplication.dto.UserDto;
import com.chronoVesta.SecurityApp.SecurityApplication.service.AuthService;
import com.chronoVesta.SecurityApp.SecurityApplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                        HttpServletResponse response) {
        LoginResponseDto loginResponseDto = authService.login(loginDto);
        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside the cookie"));
        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
//      Add a new Refresh token to cookie
        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }


//    logout EndPoint
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//        Check if the cookies array is present and contains the refresh token
        Cookie[] cookies = request.getCookies();
        if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> "refreshToken".equals(cookie.getName()))) {
//            If no refresh token is found in cookie, return a message indicating that the user is already logged out
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already logged off or the session is expired");
        }

//        Get refresh token from the cookie
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookie"));

//        Invalidate the session associated with the refresh token
        authService.logout(refreshToken);

//        Remove the refreshToken from the cookie
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        cookie.setMaxAge(0); // Set the cookies max age to zero to delete it
        cookie.setPath("/"); // Ensure the correct cookie is removed
        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }

}
