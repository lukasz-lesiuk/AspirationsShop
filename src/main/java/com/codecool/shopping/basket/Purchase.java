package com.codecool.shopping.basket;

import com.codecool.IDGenerator;
import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.shopping.basket.Basket;
import com.codecool.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Purchase {
    IDGenerator generator = new IDGenerator();
    Basket basket;
    Customer activeCustomer;
    String transactionID;
    Transaction newTransaction;
    List<Product> productsToUpdateInWarehouse;

    public Purchase(Basket basket, Customer activeCustomer) {
        this.basket = basket;
        this.activeCustomer = activeCustomer;
        this.transactionID = generator.generateID();
        this.newTransaction = finalizeTransaction();
        this.productsToUpdateInWarehouse = updateProductsToWarehouse(basket);
    }

    public Transaction finalizeTransaction() {
        Transaction newTransaction = new Transaction(transactionID, activeCustomer.getCustomerId(), basket);

        return newTransaction;
    }

    public List<Product> updateProductsToWarehouse(Basket basket) {
        List<Product> updatedProducts = new ArrayList<>();

        for(Map.Entry<Product, Integer> entry : basket.getTransactionProducts().entrySet()) {
            entry.getKey().updateWarehouse(entry.getValue());
            updatedProducts.add(entry.getKey());
        }

        return updatedProducts;
    }

    // jak przekażemy List<Product> do SQLProductDAO?
}
