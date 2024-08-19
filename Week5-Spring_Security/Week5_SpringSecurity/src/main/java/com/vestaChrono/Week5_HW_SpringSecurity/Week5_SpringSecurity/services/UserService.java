package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.LoginDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.SignUpDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.dto.UserDto;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.User;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.exception.ResourceNotFoundException;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories.UserRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + username + " not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new BadCredentialsException("User with email already exists " + signUpDto.getEmail());
        }

        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
