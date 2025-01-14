package dto;

import com.grocery_booking_api.model.GroceryItem;

public class InventoryUpdateResponse {
    private String message;
    private GroceryItem groceryItem;

    // Constructor
    public InventoryUpdateResponse(String message, GroceryItem groceryItem) {
        this.message = message;
        this.groceryItem = groceryItem;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GroceryItem getGroceryItem() {
        return groceryItem;
    }

    public void setGroceryItem(GroceryItem groceryItem) {
        this.groceryItem = groceryItem;
    }
}
