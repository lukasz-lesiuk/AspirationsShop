package com.codecool.shopping;

import com.codecool.customer.Customer;

public class ShoppingController {
    Customer activeCustomer;
    Basket basket;
    Browse browse;

    public ShoppingController(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
        this.basket = new Basket();
        this.browse = new Browse();
    }
}
