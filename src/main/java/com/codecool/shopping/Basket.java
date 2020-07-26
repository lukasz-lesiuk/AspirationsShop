package com.codecool.shopping;

import com.codecool.transaction.Product;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private Map<Product, Integer> MapProduckt;

    public Basket(){
        this.MapProduckt = new HashMap<>();
    }

    public Map<Product, Integer> getTransactionProducts() {
        return MapProduckt;
    }

    public void setProduct(Product product, Integer quantity) {
        if (checkInventory(product, quantity)){
            MapProduckt.put(product, quantity);
        }
    }

    public boolean checkInventory(Product product, Integer quantity){
        return product.getQuantity() > quantity ? true : false;
    }

    public void deleteProduct(Product product) {
        if (MapProduckt.containsKey(product)) {
            MapProduckt.remove(product);
        }
    }

    public Product getProduct(String productName){
        Product choosenProduct = null;
        for (Product product :  MapProduckt.keySet()){
            if(product.getProductID().equals(productName)){
                return product;}
        }
        return choosenProduct;
    }

    public void clearBasket(){
        MapProduckt.clear();
    }

    public int getTotalBasketValue(){
        int totalValue = 0;
        for (Product product :  MapProduckt.keySet()){
            totalValue+= MapProduckt.get(product) * product.getPrice();
        }
        return totalValue;
    }

}


