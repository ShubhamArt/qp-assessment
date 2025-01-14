package com.grocery_booking_api.service;
import com.grocery_booking_api.model.User;
import com.grocery_booking_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        return userRepository.save(user); // Saves the new user into the database
    }

    // Find user by username (for authentication)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username); // Fetch user by username
    }

    // Check if a username already exists
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username); // Returns true if the username exists
    }
}
