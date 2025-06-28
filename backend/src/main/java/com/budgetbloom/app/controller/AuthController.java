package com.budgetbloom.app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.budgetbloom.app.dto.AuthRequest;
import com.budgetbloom.app.service.AuthService;

import jakarta.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Define endpoints for authentication here
    // For example, login, register, etc.
   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
       return ResponseEntity.ok("Login successful");
   }

}
