package com.codecool.transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    private HashMap<String, List<Integer>> transactionItems;
    private String transactionID, customerID;
    private LocalDateTime transactionDate;

    public Transaction(String transactionID, String customerID, HashMap<Product, Integer> purchase) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.transactionItems = createMapOfItems(purchase);
        this.transactionDate = LocalDateTime.now();
    }
    // przeładowany konstruktor, który ustawi datę wg rekordu z bazy danych

    private HashMap<String, List<Integer>> createMapOfItems(HashMap<Product, Integer> purchase) {
        HashMap<String, List<Integer>> mapOfItems = new HashMap<String, List<Integer>>();

        for(Map.Entry<Product, Integer> entry : purchase.entrySet()) {
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

}
