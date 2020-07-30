package com.codecool.product;

import java.util.List;

public class ProductView {

    public void printProduct(Product product){
        System.out.print(product.getProductName() + ". ");
        System.out.print(product.getCategory() + ". ");
        //System.out.print(product.getDescription() + " ");
        System.out.print(product.getPrice() + " ");
        System.out.println(product.getQuantity());
        System.out.println("");
    }

    public void printProductList(List<Product> productList){
        int counter = 1;
        for(Product product : productList){
            System.out.print(counter + ". ");
            printProduct(product);
            counter++;
        }

    }
}
