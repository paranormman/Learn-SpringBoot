package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.exception.ResourceNotFoundException;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + username + " not found"));
    }
}
