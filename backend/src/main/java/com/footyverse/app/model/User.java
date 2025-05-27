package com.footyverse.app.model;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String username;

    @NonNull
    @Email 
    private String email;
    private String password;
    private String role;
    private String profilePicture;
    private String bio;
    private String location;
    private String dateOfBirth;
    private String createdAt;
    private String updatedAt;
    private String lastLogin;
    private String favoritePlayer;
    private String favoriteLeague;
    private String favoriteClub;

    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public String getUsername() {
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
    
    public void setPassword(String password) {
        this.password = password;
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
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    public String getFavoritePlayer() {
        return favoritePlayer;
    }
    public void setFavoritePlayer(String favoritePlayer) {
        this.favoritePlayer = favoritePlayer;
    }
    public String getFavoriteLeague() {
        return favoriteLeague;
    }
    public void setFavoriteLeague(String favoriteLeague) {
        this.favoriteLeague = favoriteLeague;
    }
    public String getFavoriteClub() {
        return favoriteClub;
    }
    public void setFavoriteClub(String favoriteClub) {
        this.favoriteClub = favoriteClub;
    }

    public User() {
    }
    public User(UUID id, String username, String email, String password, String role, String profilePicture, String bio, String location, String dateOfBirth, String createdAt, String updatedAt, String lastLogin, String favoritePlayer, String favoriteLeague, String favoriteClub) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.favoritePlayer = favoritePlayer;
        this.favoriteLeague = favoriteLeague;
        this.favoriteClub = favoriteClub;
    }
}
