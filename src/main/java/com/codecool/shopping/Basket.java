package com.codecool.shopping;

import com.codecool.product.Product;

import java.util.*;

public class Basket {
    private Map<Product, Integer> productsMap;

    public Basket(){
        this.productsMap = new HashMap<>();
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

    public void deleteProduct(Product product) {
        if (productsMap.containsKey(product)) {
            productsMap.remove(product);
        }
    }

    public Product getProduct(String productName){
        Product chosenProduct = null;
        for (Product product :  productsMap.keySet()){
            if(product.getProductID().equals(productName)){
                return product;}
        }
        return chosenProduct;
    }

    public void clearBasket(){
        productsMap.clear();
    }

    public int getTotalBasketValue(){
        int totalValue = 0;
        for (Product product :  productsMap.keySet()){
            totalValue+= productsMap.get(product) * product.getPrice();
        }
        return totalValue;
    }
}