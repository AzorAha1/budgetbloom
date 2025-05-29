package com.footyverse.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.footyverse.app.dto.UserDTO;
import com.footyverse.app.model.User;
import com.footyverse.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // This class will handle the HTTP requests related to user operations
    // It will use the UserService to perform the operations
    // It will also handle the exceptions and return the appropriate response
    // For example, if a user is not found, it will return a 404 error
    // If a user is created successfully, it will return a 201 status code
    // If there is a validation error, it will return a 400 status code

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // This method will handle the GET request to fetch all users

    @GetMapping
    public List<UserDTO> getusers() {
       return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> adduser(@RequestBody User user) {
        boolean useradded = userService.addUser(user);
        if (!useradded) {
            return ResponseEntity.status(404).body("User Addition Failed");
        }
        return ResponseEntity.status(201).body("User Added");
    }
    // This method will handle the GET request to fetch a user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserbyid(@PathVariable String id) {
        UserDTO userdto = userService.getUserDTOById(id);
        if (userdto == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(userdto);
    }
    // This method will handle the PUT request to update a user
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        boolean updateduser = userService.updateUser(id, user);
        if (!updateduser) {
            return ResponseEntity.status(404).body("User Update Failed");
        }
        return ResponseEntity.status(200).body("User Updated");
    }
    // this method is to get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getuserbyemail(@PathVariable String email) {
        UserDTO userfound = userService.getUserByEmail(email);
        if (userfound == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(userfound);
    }
}
