package com.chronoVesta.SecurityApp.SecurityApplication.service;

import com.chronoVesta.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.chronoVesta.SecurityApp.SecurityApplication.repository.UserRepository;
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
                .orElseThrow(() ->new ResourceNotFoundException("User with email " + username +" is not found"));
    }
}
