package com.codecool.transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;


public class Transaction {
    private HashMap<Product, Integer> transactionItems;
    private String transactionID, customerID;
    private Date transactionDate;

    public HashMap<Product, Integer> getTransactionItems() {
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
        this.transactionItems = basket.getTransactionProducts();
        this.transactionDate = Date.valueOf(LocalDate.now());
    }
    // przeładowany konstruktor, który ustawi datę wg rekordu z bazy danych

//    private HashMap<String, List<Integer>> createMapOfItems(HashMap<Product, Integer> purchase) {
//        HashMap<String, List<Integer>> mapOfItems = new HashMap<String, List<Integer>>();
//
//        for(Map.Entry<Product, Integer> entry : purchase.entrySet()) {
//            String key = entry.getKey().getProductID();
//            Integer price = entry.getKey().getPrice();
//            Integer quantity = entry.getValue();
//
//            List<Integer> value = new ArrayList<>();
//            value.add(price);
//            value.add(quantity);
//
//            mapOfItems.put(key, value);
//        }
//        return mapOfItems;
//    }

}
