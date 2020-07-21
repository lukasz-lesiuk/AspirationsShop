package com.codecool;

import java.time.LocalDate;

public class Transaction {
    private int quantity, price;
    private String transactionID, productID, customerID;
    private LocalDate transactionDate;

    public Transaction(String transactionID, String productID, String customerID, int quantity, int price) {
        this.transactionID = transactionID;
        this.productID = productID;
        this.customerID = customerID;
        this.quantity = quantity;
        this.price = price;
        this.transactionDate = LocalDate.now();
    }

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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
