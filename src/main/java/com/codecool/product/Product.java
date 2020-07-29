package com.codecool.product;

public class Product {
    private String name;
    private String description;
    private int cost;
    private int quantity;
    private String category;

    public Product(String name, String description, int cost, int quantity, String category) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
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
}
