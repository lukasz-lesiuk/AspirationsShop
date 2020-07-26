package com.codecool.transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Transaction {
    private HashMap<String, List<Integer>> transactionItems;
    private String transactionID, customerID;
    private Date transactionDate;

    public HashMap<String, List<Integer>> getTransactionItems() {
        return transactionItems;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Transaction(String transactionID, String customerID, Basket basket) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.transactionItems = createMapOfItems(basket);
        this.transactionDate = Date.valueOf(LocalDate.now());
    }

    public Transaction(String transactionID, String customerID, HashMap<String, List<Integer>> transactionItems, Date date) {
        // przeładowany konstruktor w celu uzyskania listy produktów
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.transactionItems = transactionItems;
        this.transactionDate = date;
    }

    // przeładowany konstruktor, który ustawi datę wg rekordu z bazy danych

    private HashMap<String, List<Integer>> createMapOfItems(Basket basket) {
        HashMap<String, List<Integer>> mapOfItems = new HashMap<>();

        for(Map.Entry<Product, Integer> entry : basket.getTransactionProducts().entrySet()) {
            String key = entry.getKey().getProductID();
            Integer price = entry.getKey().getPrice();
            Integer quantity = entry.getValue();

            List<Integer> value = new ArrayList<>();
            value.add(price);
            value.add(quantity);

            mapOfItems.put(key, value);
        }
        return mapOfItems;
    }

    public Integer calculateTotalValue() {
        Integer totalValue = 0;

        for(Map.Entry<String, List<Integer>> entry : transactionItems.entrySet()) {
            totalValue += entry.getValue().get(0) * entry.getValue().get(1);
        }

        return totalValue;
    }
}
