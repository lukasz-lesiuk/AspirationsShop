package com.codecool;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    // to change Object for product class
    private Map<Object, Integer> MapProduckt;

    Basket(){
        this.MapProduckt = new HashMap<>();
    }

    public Map<Object, Integer> getMapProduckt() {
        return MapProduckt;
    }

    public void addProduct(Object product) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.put(product, MapProduckt.get(product) + 1);
        } else {
            MapProduckt.put(product, 1);
        }
    }
    public void addProduct(Object product, Integer quantity) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.put(product, MapProduckt.get(product) + quantity);
        } else {
            MapProduckt.put(product, quantity);
        }
    }
//    public void deleteProduct(Object product, Integer quantity) {
//        if (MapProduckt.containsKey(product)) {
//            MapProduckt.remove(product);
//        }
//    }

    public void deleteProduct(Object product) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.remove(product);
        }
    }


    public void clearBasket(){
        MapProduckt.clear();
    }

    public int getTotalBasketValue(){
        int totalValue = 0;
        for (Object product :  MapProduckt.keySet()){
            totalValue+= MapProduckt.get(product);
        }
        return totalValue;
    }
}


