package com.grocery_booking_api.controller;

import com.grocery_booking_api.model.GroceryItem;
import com.grocery_booking_api.model.User;
import com.grocery_booking_api.repository.UserRepository;
import com.grocery_booking_api.service.GroceryItemService;
import dto.InventoryUpdateRequest;
import dto.InventoryUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private GroceryItemService groceryItemService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/admin-login")
    public ResponseEntity<String> adminLogin(@RequestBody User loginRequest) {
        // Find the user by username
        User user = userRepository.findByUsername(loginRequest.getUsername());

        // Check if user exists
        if (user == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.UNAUTHORIZED);
        }

        // Verify the password
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Check if the user is an admin
            if ("ROLE_ADMIN".equals(user.getRole())) {
                return new ResponseEntity<>("Admin login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not an admin user", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/add-item")
    public ResponseEntity<?> addItem(@RequestBody GroceryItem groceryItem) {
        // Check if the grocery item is valid
        if (groceryItem == null || groceryItem.getName() == null || groceryItem.getPrice() <= 0 || groceryItem.getInventory() < 0) {
            return new ResponseEntity<>("Invalid grocery item data", HttpStatus.BAD_REQUEST);
        }

        // Add the grocery item to the database
        GroceryItem addedItem = groceryItemService.addItem(groceryItem);

        // Return a response indicating success, with a 201 Created status
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }


    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllItems() {
        List<GroceryItem> groceryItems = groceryItemService.getAllItems();

        // Check if the list is empty
        if (groceryItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no items
        }

        // Return the list of grocery items with a 200 OK status
        return new ResponseEntity<>(groceryItems, HttpStatus.OK);
    }


    @PutMapping("/update-item/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) {
        GroceryItem existingItem = groceryItemService.getGroceryItemById(id);
        if (existingItem == null) {
            return new ResponseEntity<>("Item not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        // Update the item
        groceryItem.setId(id);  // Ensure the id of the grocery item is set for the update
        GroceryItem updatedItem = groceryItemService.updateItem(id, groceryItem);

        // Return a response indicating success
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        try {
            groceryItemService.deleteGroceryItem(id);
            return new ResponseEntity<>("Grocery item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting grocery item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/manage-inventory/{id}")
    public ResponseEntity<?> manageInventory(@PathVariable Long id, @RequestBody InventoryUpdateRequest inventoryUpdateRequest) {
        // Check if the item exists

        GroceryItem existingItem = groceryItemService.getGroceryItemById(id);;
        if (existingItem == null) {
            return new ResponseEntity<>("Item not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        // Check for valid quantity (quantity cannot be negative)
        if (inventoryUpdateRequest.getInventory() < 0) {
            return new ResponseEntity<>("Quantity cannot be negative", HttpStatus.BAD_REQUEST);
        }

        // Update the inventory
        existingItem.setInventory(inventoryUpdateRequest.getInventory());
        GroceryItem updatedItem = groceryItemService.updateItem(existingItem);

        // Return success response
        return new ResponseEntity<>(new InventoryUpdateResponse("Inventory updated successfully", updatedItem), HttpStatus.OK);
    }
}
