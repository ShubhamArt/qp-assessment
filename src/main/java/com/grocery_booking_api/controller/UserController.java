package com.grocery_booking_api.controller;

import com.grocery_booking_api.model.GroceryItem;
import com.grocery_booking_api.model.Order;
import com.grocery_booking_api.model.User;
import com.grocery_booking_api.repository.UserRepository;
import com.grocery_booking_api.service.GroceryItemService;
import com.grocery_booking_api.service.OrderService;
import dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private GroceryItemService groceryItemService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderService orderService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        // Find the user by username
        User user = userRepository.findByUsername(loginRequest.getUsername());

        // Check if user exists
        if (user == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.UNAUTHORIZED);
        }

        // Verify the password
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            if ("ROLE_USER".equals(user.getRole())) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not a user role", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Here you handle the order creation logic
            Order savedOrder = orderService.createOrder(orderRequest);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllItems() {
        List<GroceryItem> groceryItems = groceryItemService.getAllItems();

        // If there are no grocery items in the database, return a 204 No Content status
        if (groceryItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Return the list of grocery items with a 200 OK status
        return new ResponseEntity<>(groceryItems, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if the user already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }


}
