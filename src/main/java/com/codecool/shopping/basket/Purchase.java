package com.codecool.shopping.basket;

import com.codecool.idGenerator.IDGenerator;
import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.product.ProductDAO;
import com.codecool.product.ProductDAOPSQL;
import com.codecool.product.ProductView;
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
        //this.productsToUpdateInWarehouse = updateProductsToWarehouse();
    }

    public void finalizeTransaction() {
        this.newTransaction = new Transaction(transactionID, activeCustomer.getCustomerId(), basket);
        TransactionDAO sqlTransactionDAO = new SQLTransactionDAO();
        sqlTransactionDAO.addTransaction(newTransaction);
    }

    public List<Product> updateProductsToWarehouse() {
        List<Product> updatedProducts = new ArrayList<>();

        for(Map.Entry<Product, Integer> entry : basket.getTransactionProducts().entrySet()) {
            entry.getKey().updateWarehouse(entry.getValue());
            updatedProducts.add(entry.getKey());
        }
        return updatedProducts;
    }

    public void updateProductsQuanity(){
        List<Product> productListToUpdate = updateProductsToWarehouse();
        ProductView productView = new ProductView();

        productView.pressEnter();
        productView.printProductList(productListToUpdate);

        productView.pressEnter();
        ProductDAO productDAOPSQL = new ProductDAOPSQL();
        for (Product product : productListToUpdate) {
            productDAOPSQL.updateProductQuantity(product, product.getQuantity());
        }
    }
}
