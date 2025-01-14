package com.grocery_booking_api.repository;
import com.grocery_booking_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find user by username
    User findByUsername(String username);

    // Check if a username already exists
    boolean existsByUsername(String username);
}
