package com.grocery_booking_api.repository;
import com.grocery_booking_api.model.GroceryItem;
import com.grocery_booking_api.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Method to delete order items by associated grocery item
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.groceryItem = :groceryItem")
    void deleteByGroceryItem(GroceryItem groceryItem);
}
