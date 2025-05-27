package com.footyverse.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footyverse.app.repository.UserRepository;
import com.footyverse.app.model.User;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // add user
    public boolean addUser(User user) {
       if (user != null) 
       {
        userRepository.save(user);
        return true;
       }
       return false;
    }
    public User getUserById(String id) {
        for (User user : userRepository.findAll()) {
            if (user.getId().toString().equals(id)) {
                return user;
            }
        }
        return null;
    }
    // update user
    public boolean updateUser(String id, User user) {
        for (User theuser : userRepository.findAll()) {
            if (theuser.getId().toString().equals(id)) {
                theuser.setUsername(user.getUsername());
                theuser.setEmail(user.getEmail());
                theuser.setRole(user.getRole());
                theuser.setProfilePicture(user.getProfilePicture());
                theuser.setBio(user.getBio());
                theuser.setLocation(user.getLocation());
                theuser.setDateOfBirth(user.getDateOfBirth());
                theuser.setCreatedAt(user.getCreatedAt());
                theuser.setUpdatedAt(user.getUpdatedAt());
                theuser.setLastLogin(user.getLastLogin());
                theuser.setFavoritePlayer(user.getFavoritePlayer());
                theuser.setFavoriteLeague(user.getFavoriteLeague());
                theuser.setFavoriteClub(user.getFavoriteClub());

                return true;
            }
        }
        return false;
    }

}
