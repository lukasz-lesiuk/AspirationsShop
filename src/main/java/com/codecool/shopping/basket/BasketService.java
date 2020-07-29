package com.codecool.shopping.basket;

import com.codecool.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasketService {

    protected Map<Product, Integer> productsMap;

    public BasketService(){
        this.productsMap = new LinkedHashMap<>();
    }

//    public Map<Product, Integer> getProductsMap() {
//        return productsMap;
//    }

    public void setProductService(Product product, Integer quantity) {
        if (checkInventoryService(product, quantity)){
            productsMap.put(product, quantity);
        }
    }

    public void addProductService(Product product, Integer quantity) {
        if (checkInventoryService(product, quantity)) {
            productsMap.put(product, quantity);
        }
    }

    public boolean checkInventoryService(Product product, Integer quantity){
        return product.getQuantity() > quantity ? true : false;
    }

    public void removeProductFromBasketService(Product product) {
        if (productsMap.containsKey(product)) {
            productsMap.remove(product);
        }
    }

    public Product getProductService(Integer choice) {
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

    public Integer getTotalBasketValueService(){
        Integer totalValue = 0;
        for (Product product : productsMap.keySet()){
            totalValue+= productsMap.get(product) * product.getPrice();
        }
        return totalValue;
    }
}
