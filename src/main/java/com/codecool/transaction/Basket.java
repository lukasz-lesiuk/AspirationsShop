package com.codecool.transaction;

import java.util.HashMap;

public class Basket {
    private HashMap<Product, Integer> transactionProducts;

    public HashMap<Product, Integer> getTransactionProducts() {
        return transactionProducts;
    }
}
