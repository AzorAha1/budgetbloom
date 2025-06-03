package com.footyverse.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.footyverse.app.model.User;
import com.footyverse.app.repository.UserRepository;

import java.util.Optional;
import java.util.ArrayList;

@Service
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the User from the database using the repository
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) { // Check if the Optional is empty
            throw new UsernameNotFoundException("Username not found: " + email);
        }
        User user = userOptional.get(); // Retrieve the value from the Optional
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            new ArrayList<>()
        );
    }
}