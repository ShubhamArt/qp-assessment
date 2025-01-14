package com.grocery_booking_api.service;

import com.grocery_booking_api.exception.GroceryItemNotFoundException;
import com.grocery_booking_api.model.GroceryItem;
import com.grocery_booking_api.repository.GroceryItemRepository;
import com.grocery_booking_api.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public GroceryItem getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id)
                .orElseThrow(() -> new GroceryItemNotFoundException("Grocery item with id " + id + " not found"));
    }
    // Method to delete a GroceryItem
    @Transactional
    public void deleteGroceryItem(Long id) {
        // Fetch the grocery item
        GroceryItem groceryItem = getGroceryItemById(id);

        // Delete all associated order items
        orderItemRepository.deleteByGroceryItem(groceryItem);

        // Now delete the grocery item
        groceryItemRepository.delete(groceryItem);
    }

    public GroceryItem addItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem updateItem(Long id, GroceryItem groceryItem) {
        groceryItem.setId(id);
        return groceryItemRepository.save(groceryItem);
    }

    public GroceryItem updateItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem); // This will update the grocery item in the DB
    }

    public void deleteItem(Long id) {
        groceryItemRepository.deleteById(id);
    }

    public GroceryItem manageInventory(Long id, int quantity) {
        GroceryItem item = groceryItemRepository.findById(id).orElseThrow();
        item.setInventory(item.getInventory() + quantity);
        return groceryItemRepository.save(item);
    }
}
