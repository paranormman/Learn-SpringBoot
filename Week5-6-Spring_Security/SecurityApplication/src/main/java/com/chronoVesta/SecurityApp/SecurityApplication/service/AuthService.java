package com.chronoVesta.SecurityApp.SecurityApplication.service;

import com.chronoVesta.SecurityApp.SecurityApplication.dto.LoginDto;
import com.chronoVesta.SecurityApp.SecurityApplication.dto.LoginResponseDto;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

//        Generate a new session for the user
        sessionService.generateNewSession(user, refreshToken);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);

//        Validate session with refresh token
        sessionService.validateRefreshToken(refreshToken);
        User user = userService.getUserById(userId);

//        Generate fresh AT and RT pair and store new session
        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

//        Remove the session associated with refresh token and generate a new session with new RT
        sessionService.removeSession(refreshToken);
        sessionService.generateNewSession(user, newRefreshToken);

        return new LoginResponseDto(user.getId(), newAccessToken, newRefreshToken);

    }

    public void logout(String refreshToken) {
        sessionService.removeSession(refreshToken);
    }
}
