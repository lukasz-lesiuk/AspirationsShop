package com.codecool.shopping.browse;

import com.codecool.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Browse {
    List<Product> productListByCategory = new ArrayList<>();
    String category = "";

    public List<Product> getProductListByCategory() {
        return productListByCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addProduct(Product product){
        productListByCategory.add(product);
    }

    public String getProductByCategory(String category){
        String quer = "SELECT * FROM products WHERE category ='" + category + "';";
        return quer;
    }
}
