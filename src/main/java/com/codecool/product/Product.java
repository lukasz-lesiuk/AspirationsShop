package com.codecool.product;

public class Product {
    private String productId;
    private String productName;
    private String description;
    private int price;
    private int quantity;
    private String category;



    public Product(String productId, String productName, String description, int price, int quantity, String category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return productName;
    }

    public int getCost() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
