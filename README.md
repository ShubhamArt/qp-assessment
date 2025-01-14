# Grocery Booking API Documentation

This document provides detailed information about the Grocery Booking API, which allows users to view, order, and manage grocery items. The system uses Java Spring Boot for the backend, Hibernate ORM for database operations, and MySQL as the database.

---

## Tech Stack

- **Backend**: Java Spring Boot
- **ORM**: Hibernate
- **Database**: MySQL

---

## User Endpoints

### 1. User Login

- **Endpoint**: `POST http://localhost:8080/user/login`

#### Request Body:
{
    "username": "user",
    "password": "user"
}
#### Response:
{
    "message": "Login successful"
}
---
### 2. Get Grocery Items (User)
- **Endpoint**: GET http://localhost:8080/user/items
#### Response:
[
    {
        "id": 1,
        "name": "watch",
        "price": 200.0,
        "inventory": 40
    },
    {
        "id": 2,
        "name": "handwash",
        "price": 60.0,
        "inventory": 9
    },
    {
        "id": 3,
        "name": "salt",
        "price": 50.0,
        "inventory": 8
    }
]
---
#### 3. Create Order
- **Endpoint**: PUT http://localhost:8080/user/place-order
#### Request Body:
{
    "items": [
        {
            "id": 3,
            "price": 50,
            "quantity": 2
        },
        {
            "id": 2,
            "price": 60,
            "quantity": 1
        }
    ],
    "totalAmount": 160
}
#### Response:
{
    "id": 3,
    "user": {
        "id": 1,
        "username": "user",
        "password": "$2a$10$S057X5PpLvPSQtKZE989zeR4ffKR3XUOMAcv6VTY60dS0cGMsFV5e",
        "role": "USER"
    },
    "items": [
        {
            "id": 5,
            "groceryItem": {
                "id": 3,
                "name": "salt",
                "price": 50.0,
                "inventory": 8
            },
            "quantity": 2,
            "price": 50.0
        },
        {
            "id": 6,
            "groceryItem": {
                "id": 2,
                "name": "handwash",
                "price": 60.0,
                "inventory": 9
            },
            "quantity": 1,
            "price": 60.0
        }
    ],
    "totalAmount": 160.0
}
##### Note: When an order is created, the quantity of the products will be automatically deducted from the stock.


Apologies for the confusion earlier! Here's the complete README.md file with the full content, including all the user and admin endpoints, formatted in markdown:

markdown
Copy code
# Grocery Booking API Documentation

This document provides detailed information about the Grocery Booking API, which allows users to view, order, and manage grocery items. The system uses Java Spring Boot for the backend, Hibernate ORM for database operations, and MySQL as the database.

---

## Tech Stack

- **Backend**: Java Spring Boot
- **ORM**: Hibernate
- **Database**: MySQL

---

## User Endpoints

### 1. User Login

- **Endpoint**: `POST http://localhost:8080/user/login`

#### Request Body:

{
    "username": "user",
    "password": "user"
}
#### Response Body:
{
    "message": "Login successful"
}
---
### 2. Get Grocery Items (User)
Endpoint: GET http://localhost:8080/user/items
#### Response Body:

[
    {
        "id": 1,
        "name": "watch",
        "price": 200.0,
        "inventory": 40
    },
    {
        "id": 2,
        "name": "handwash",
        "price": 60.0,
        "inventory": 9
    },
    {
        "id": 3,
        "name": "salt",
        "price": 50.0,
        "inventory": 8
    }
]
---
#### 3. Create Order
- **Endpoint**: PUT http://localhost:8080/user/place-order
#### Request Body:

{
    "items": [
        {
            "id": 3,
            "price": 50,
            "quantity": 2
        },
        {
            "id": 2,
            "price": 60,
            "quantity": 1
        }
    ],
    "totalAmount": 160
}
#### Response Body:
{
    "id": 3,
    "user": {
        "id": 1,
        "username": "user",
        "password": "$2a$10$S057X5PpLvPSQtKZE989zeR4ffKR3XUOMAcv6VTY60dS0cGMsFV5e",
        "role": "USER"
    },
    "items": [
        {
            "id": 5,
            "groceryItem": {
                "id": 3,
                "name": "salt",
                "price": 50.0,
                "inventory": 8
            },
            "quantity": 2,
            "price": 50.0
        },
        {
            "id": 6,
            "groceryItem": {
                "id": 2,
                "name": "handwash",
                "price": 60.0,
                "inventory": 9
            },
            "quantity": 1,
            "price": 60.0
        }
    ],
    "totalAmount": 160.0
}
##### Note: When an order is created, the quantity of the products will be automatically deducted from the stock.
---
# Admin Endpoints
#### 1. Admin Login
- **Endpoint**: POST http://localhost:8080/admin/admin-login
### Request Body :
{
    "name": "admin",
    "password": "admin"
}
### Response:
{
    "message": "Admin login successful"
}
---
### 2. Add New Grocery Items to the System
- **Endpoint**: POST http://localhost:8080/admin/add-item
### Request Body :
{
    "name": "maggi",
    "price": 20.0,
    "inventory": 10
}
### Response:
{
    "id": 5,
    "name": "maggi",
    "price": 20.0,
    "inventory": 10
}
{
    "id": 5,
    "name": "maggi",
    "price": 20.0,
    "inventory": 10
}
---
### 3. View Existing Grocery Items (Admin)
- **Endpoint**: GET http://localhost:8080/admin/items
### Response:
[
    {
        "id": 1,
        "name": "watch",
        "price": 200.0,
        "inventory": 40
    },
    {
        "id": 2,
        "name": "handwash",
        "price": 60.0,
        "inventory": 9
    },
    {
        "id": 3,
        "name": "salt",
        "price": 50.0,
        "inventory": 8
    },
    {
        "id": 5,
        "name": "maggi",
        "price": 20.0,
        "inventory": 10
    }
]
---
### 4. Remove Grocery Items from the System
- **Endpoint**: DELETE http://localhost:8080/admin/delete-item/{itemId}
- example : DELETE http://localhost:8080/admin/delete-item/3
##### Response: 
{
    "message": "Grocery item deleted successfully"
}
---
### 5. Update Details (e.g., Name, Price) of Existing Grocery Items
- **Endpoint**: PUT http://localhost:8080/admin/update-item/{itemId}
- example : PUT http://localhost:8080/admin/update-item/2
### Request :
{
    "name": "toast",
    "price": 30.0,
    "inventory": 10
}
### Response
{
    "id": 2,
    "name": "toast",
    "price": 30.0,
    "inventory": 10
}
---
### 6. Manage Inventory Levels of Grocery Items
- **Endpoint**: PATCH http://localhost:8080/admin/manage-inventory/{itemId}
-example : PATCH http://localhost:8080/admin/manage-inventory/1
### Request:
{
    "inventory": 25
}
### Response:
{
    "message": "Inventory updated successfully",
    "groceryItem": {
        "id": 1,
        "name": "watch",
        "price": 200.0,
        "inventory": 25
    }
}
---
#### Error Handling
The API will return appropriate HTTP status codes and error messages for invalid requests. Common error responses include:

- 400 Bad Request: Invalid request body or parameters.
- 401 Unauthorized: Unauthorized access, typically when authentication fails.
- 404 Not Found: Resource not found (e.g., when an item does not exist).
- 500 Internal Server Error: An error occurred on the server.
