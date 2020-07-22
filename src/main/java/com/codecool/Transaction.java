package com.codecool;

import java.time.LocalDateTime;

public class Transaction {
    private int quantity, price;
    private String transactionID, productID, customerID;
    private LocalDateTime transactionDate;

    public Transaction(String transactionID, String productName, String customerID, int quantity, int price) {
        this.transactionID = transactionID;
        this.productID = productName;
        this.customerID = customerID;
        this.quantity = quantity;
        this.price = price;
        this.transactionDate = LocalDateTime.now();
    }

    // constructor will take objects: Basket, Customer

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getProductID() {
        return productID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}
