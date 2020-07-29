package com.codecool.shopping.basket;

import com.codecool.product.Product;

import java.util.*;


public class Basket extends BasketService {

    public Basket(){
        super();
    }

    public Map<Product, Integer> getTransactionProducts() {
        return productsMap;
    }

    public void setProduct(Product product, Integer quantity) {
        setProductService(product, quantity);
    }

    public void addProduct(Product product, Integer quantity) {
        addProductService(product, quantity);
    }

    public void removeProductFromBasket(Product product) {
        removeProductFromBasketService(product);
    }

    public Product getProduct(Integer choice) {
        return getProductService(choice);
    }

    public void clearBasket(){
        productsMap.clear();
    }

    public Integer getTotalBasketValue(){
        return getTotalBasketValueService();
    }
}