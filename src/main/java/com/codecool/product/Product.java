package com.codecool.product;

public class Product {
    private String productId;
    private String productName;
    private String description;
    private int price;
    private int quantity;
    private String category;



    public Product(String productId, String productName, String description, String price, String quantity, String category) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = Integer.parseInt(price);
        this.quantity = Integer.parseInt(quantity);
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
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

    public String getProductID() {
        return productId;
    }

    public void updateWarehouse(Integer numberOfSoldItems) {
        this.quantity = quantity - numberOfSoldItems;
    }
}
