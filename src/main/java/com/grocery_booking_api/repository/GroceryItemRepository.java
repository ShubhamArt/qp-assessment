package com.grocery_booking_api.repository;

import com.grocery_booking_api.model.GroceryItem;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{
}
