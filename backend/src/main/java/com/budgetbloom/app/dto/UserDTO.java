package com.budgetbloom.app.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Id;

public class UserDTO {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String role;
    private String profilePicture;
    private String bio;
    private String location;
    private String dateOfBirth;
    private BigDecimal currentAccountbalance; 

    public UserDTO(UUID id, String username, String email, String role, String profilePicture, String bio, String location, String dateOfBirth, BigDecimal currentAccountbalance) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.currentAccountbalance = currentAccountbalance;


    }
    // getters and setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getCurrentAccountBalance() {
        return currentAccountbalance;
    }
    public void setCurrentAccountbalance(BigDecimal currentAccountbalance) {
        this.currentAccountbalance = currentAccountbalance;
    }
  
}
