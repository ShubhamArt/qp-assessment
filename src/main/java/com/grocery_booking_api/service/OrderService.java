package com.grocery_booking_api.service;

import com.grocery_booking_api.model.GroceryItem;
import com.grocery_booking_api.model.Order;
import com.grocery_booking_api.model.OrderItem;
import com.grocery_booking_api.model.User;
import com.grocery_booking_api.repository.GroceryItemRepository;
import com.grocery_booking_api.repository.OrderRepository;
import com.grocery_booking_api.repository.UserRepository;
import dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private GroceryItemRepository groceryItemRepository;
    public Order createOrder(OrderRequest orderRequest) {
        // Create new order object
        Order order = new Order();
        double totalAmount = 0;

        List<OrderItem> orderItems = new ArrayList<>();

        // Loop over the request items and validate grocery items
        for (OrderRequest.OrderItemRequest itemRequest : orderRequest.getItems()) {
            GroceryItem groceryItem = groceryItemRepository.findById(itemRequest.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Grocery item not found for ID: " + itemRequest.getId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setGroceryItem(groceryItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());
            orderItem.setOrder(order);  // Set the order reference in the orderItem

            orderItems.add(orderItem);

            // Calculate the total amount based on quantity and price
            totalAmount += itemRequest.getPrice() * itemRequest.getQuantity();
            // Deduct the stock after the order item has been processed
            groceryItem.setInventory(groceryItem.getInventory() - itemRequest.getQuantity());

            // Save the updated grocery item to reflect the stock deduction
            groceryItemRepository.save(groceryItem);
        }

        // Set the total amount and the items in the order
        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);

        // Assuming you want the user to be linked to the order, fetch the user from the database
        User user = userRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("User not found"));
        order.setUser(user);

        // Save the order to the database
        return orderRepository.save(order);
    }
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findAll();
    }
}
