package com.budgetbloom.app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.budgetbloom.app.dto.AuthRequest;
import com.budgetbloom.app.dto.AuthResponse;
import com.budgetbloom.app.dto.UserDTO;
import com.budgetbloom.app.model.User;
import com.budgetbloom.app.security.JwtUtil;
import com.budgetbloom.app.service.AuthService;

import jakarta.validation.constraints.Email;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil, UserService userService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    // Define endpoints for authentication here
    // For example, login, register, etc.
   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
       // authenticate user 
    try {
        User authenticatedUser = authService.authenticate(authRequest.getEmail(), authRequest.getPassword());
        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
        }
        // convert authenticated user to UserDetails
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            authenticatedUser.getEmail(),
            authenticatedUser.getPassword(),
            new ArrayList<>()
        );


        //generate token
        String loggedintoken = jwtUtil.generateToken(userDetails);
        // set last login date
        authenticatedUser.setLastLogin(LocalDate.now().toString());
        
        UserDTO userDTO = userService.convUserDTO(authenticatedUser);
        // this.token = token;
        // this.user = user;
        // this.message = message;

        // AuthResponse reponse = authRespons
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during authentication");
    }

       // return response with token
       
   }

}
