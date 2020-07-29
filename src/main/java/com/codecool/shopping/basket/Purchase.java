package com.codecool.shopping.basket;

import com.codecool.IDGenerator;
import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.transaction.SQLTransactionDAO;
import com.codecool.transaction.Transaction;
import com.codecool.transaction.TransactionDAO;

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
        this.productsToUpdateInWarehouse = updateProductsToWarehouse(basket);
    }

    public void finalizeTransaction() {
        this.newTransaction = new Transaction(transactionID, activeCustomer.getCustomerId(), basket);
        TransactionDAO sqlTransactionDAO = new SQLTransactionDAO();
        sqlTransactionDAO.addTransaction(newTransaction);
        //return addedTransactions == 0 ? false : true;
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
