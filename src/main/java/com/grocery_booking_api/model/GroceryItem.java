package com.grocery_booking_api.model;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "grocery_items")
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    private String name;
    private double price;
    private int inventory;

    @Override
    public String toString() {
        return "GroceryItem{id=" + id + ", name='" + name + "', price=" + price + ", inventory=" + inventory + '}';
    }
}
