package com.budgetbloom.app.service;

import java.lang.classfile.ClassFile.Option;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.budgetbloom.app.model.User;
import com.budgetbloom.app.repository.UserRepository;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User authenticate(String email, String password) {
        // fetch user by email
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            User user = userOptional.get();
            user.setLastLogin(LocalDate.now().toString());
            userRepository.save(user); // Update last login date
            return user;
        }
        return null; // Authentication failed
    }
}