package com.codecool.shopping.basket;

import com.codecool.product.Product;

import java.util.*;

public class Basket {
    private Map<Product, Integer> productsMap;

    public Basket(){
        this.productsMap = new LinkedHashMap<>();
    }

    public Map<Product, Integer> getTransactionProducts() {
        return productsMap;
    }

    public void setProduct(Product product, Integer quantity) {
        if (checkInventory(product, quantity)){
            productsMap.put(product, quantity);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (checkInventory(product, quantity)) {
            productsMap.put(product, quantity);
        }
    }

    public void addProduct(Product product) {
        if (checkInventory(product, 1)) {
            productsMap.put(product, 1);
        }
    }

    public boolean checkInventory(Product product, Integer quantity){
        return product.getQuantity() > quantity ? true : false;
    }

    public void removeProductFromBasket(Product product) {
        if (productsMap.containsKey(product)) {
            productsMap.remove(product);
        }
    }

    public Product getProduct(Integer choice) {
        Product chosenProduct = null;
        int counter = 0;
        for (Product product : productsMap.keySet()) {
            if (counter == choice) {
                chosenProduct = product;
            }
            counter++;
        }
        return chosenProduct;
    }

    public void clearBasket(){
        productsMap.clear();
    }

    public Integer getTotalBasketValue(){
        Integer totalValue = 0;
        for (Product product : productsMap.keySet()){
            totalValue+= productsMap.get(product) * product.getPrice();
        }
        return totalValue;
    }
}