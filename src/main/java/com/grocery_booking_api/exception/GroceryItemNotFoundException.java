package com.grocery_booking_api.exception;

public class GroceryItemNotFoundException extends RuntimeException {

    public GroceryItemNotFoundException(String message) {
        super(message);
    }

    public GroceryItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
