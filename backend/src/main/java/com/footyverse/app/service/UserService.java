package com.footyverse.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.footyverse.app.repository.UserRepository;
import com.footyverse.app.dto.UserDTO;
import com.footyverse.app.model.User;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserDTO convUserDTO(User user) {
        return new UserDTO(
            user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getProfilePicture(), user.getBio(), user.getLocation(), user.getDateOfBirth(), user.getFavoritePlayer(), user.getFavoriteLeague(), user.getFavoriteClub()
            );
    }

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // get all users
    public List<UserDTO> getAllUsers() {

        List<User> allusers = userRepository.findAll();
        List <UserDTO> allUserDTOs  = allusers.stream()
            .map(this::convUserDTO)
            .toList();
        return allUserDTOs;
    }

    // add user
    public boolean addUser(User user) {
       if (user != null) 
       {
        // Check if the user already exists
        for (User existingUser : userRepository.findAll()) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                System.out.println("User with email " + user.getEmail() + " already exists.");
                return false; // User already exists
            }
        }
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now().toString());
        userRepository.save(user);
        return true;
       }
       return false;
    }
    public UserDTO getUserDTOById(String id) {
        return userRepository.findById(UUID.fromString(id)).map(this::convUserDTO).orElse(null);
    }
    // update user
    public boolean updateUser(String id, User updateduser) {
        Optional<User> userOptional = userRepository.findById(UUID.fromString(id));
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // Update fields
            existingUser.setUsername(updateduser.getUsername());
            existingUser.setEmail(updateduser.getEmail());
            existingUser.setPassword(passwordEncoder.encode(updateduser.getPassword()));
            existingUser.setRole(updateduser.getRole());
            existingUser.setProfilePicture(updateduser.getProfilePicture());
            existingUser.setBio(updateduser.getBio());
            existingUser.setLocation(updateduser.getLocation());
            existingUser.setDateOfBirth(updateduser.getDateOfBirth());
            existingUser.setFavoritePlayer(updateduser.getFavoritePlayer());
            existingUser.setFavoriteLeague(updateduser.getFavoriteLeague());
            existingUser.setFavoriteClub(updateduser.getFavoriteClub());
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }
    public UserDTO getUserByEmail(String email) {
       return userRepository.findByEmail(email).map(this::convUserDTO).orElse(null);
    }

    public User authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            User user = userOptional.get();
            user.setLastLogin(LocalDateTime.now().toString());
            userRepository.save(user);
            return user;
        }
        return null;
    }
}
