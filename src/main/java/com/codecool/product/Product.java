package com.codecool.product;

public class Product {
    private String productID;
    private Integer price;
    private Integer quantity;
    private String productName;

    public void setQuantity(Integer quantity) {
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
