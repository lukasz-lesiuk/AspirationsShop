package com.codecool.product;

public class Product {
    private String productID;
    private String productName;
    private Integer price;
    private Integer quantity;

    public Product(String productID, String productName, Integer price, Integer quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() { return quantity; }

    public String getProductName() {return productName; }

    public void updateWarehouse(Integer numberOfSoldItems) {
        this.quantity = quantity - numberOfSoldItems;
    }
}
