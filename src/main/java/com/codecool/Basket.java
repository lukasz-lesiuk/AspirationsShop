package com.codecool;

import com.codecool.transaction.Product;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    // to change Product for product class
    private Map<Product, Integer> MapProduckt;

    Basket(){
        this.MapProduckt = new HashMap<>();
    }

    public Map<Product, Integer> getTransactionProducts() {
        return MapProduckt;
    }

    public void addProduct(Product product) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.put(product, MapProduckt.get(product) + 1);
        } else {
            MapProduckt.put(product, 1);
        }
    }
    public void addProduct(Product product, Integer quantity) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.put(product, MapProduckt.get(product) + quantity);
        } else {
            MapProduckt.put(product, quantity);
        }
    }

    public void deleteProduct(Product product) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.remove(product);
        }
    }


    public void clearBasket(){
        MapProduckt.clear();
    }

    public int getTotalBasketValue(){
        int totalValue = 0;
        for (Product product :  MapProduckt.keySet()){
            totalValue+= MapProduckt.get(product);
        }
        return totalValue;
    }
}


