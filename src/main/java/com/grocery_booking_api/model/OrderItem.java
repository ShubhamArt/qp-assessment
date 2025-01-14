package com.grocery_booking_api.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-one relationship to GroceryItem (each OrderItem corresponds to one GroceryItem)
    @ManyToOne
    @JoinColumn(name = "grocery_item_id")  // Foreign key column for GroceryItem
    private GroceryItem groceryItem;

    // Many-to-one relationship to Order (each OrderItem belongs to one Order)
    @ManyToOne
    @JoinColumn(name = "order_id")  // Foreign key column for Order
    @JsonBackReference
    private Order order;

    private int quantity;  // Quantity of the grocery item in this order item

    private double price;  // Price of the grocery item (can be different from the price in the GroceryItem entity)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroceryItem getGroceryItem() {
        return groceryItem;
    }

    public void setGroceryItem(GroceryItem groceryItem) {
        this.groceryItem = groceryItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
