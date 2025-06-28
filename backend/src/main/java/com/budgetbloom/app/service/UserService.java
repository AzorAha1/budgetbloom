package com.budgetbloom.app.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.budgetbloom.app.repository.UserRepository;
import com.budgetbloom.app.dto.UserDTO;
import com.budgetbloom.app.model.User;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserDTO convUserDTO(User user) {
        return new UserDTO(
            user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getProfilePicture(), user.getBio(), user.getLocation(), user.getDateOfBirth(),
            user.getCurrentAccountbalance()
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
        if (userRepository.existsByEmail(user.getEmail())) {
            return false; // User with this email already exists
        }
        
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now().toString());
        userRepository.save(user);
        return true;
       }
       return false;
    }

    // update user accountbalance
    public void updateAccountbalance(String id, BigDecimal amount) {
        UUID user_id = UUID.fromString(id);
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        // Update the user's account balance
        BigDecimal currentBalance = user.getCurrentAccountbalance() != null ? user.getCurrentAccountbalance() : BigDecimal.ZERO;
        user.setCurrentAccountbalance(currentBalance.add(amount));
        userRepository.save(user);
        
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
