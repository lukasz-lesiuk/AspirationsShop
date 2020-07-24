package com.codecool.transactionDAO;

import com.codecool.transaction.Product;
import com.codecool.transaction.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLTransactionDAO implements TransactionDAO {
    private String url;
    private String username;
    private String password;
    private final int TP_TRANSACTIONID = 0;
    private final int CUSTOMERID = 1;
    private final int TRANSACTION_DATE = 2;

    private final int TD_TRANSACTIONID = 0;
    private final int PRODUCTID = 1;
    private final int PRICE = 2;
    private final int QUANTITY = 3;

    public SQLTransactionDAO(String username, String password) {
        this.url = "jdbc:postgresql://localhost/aspiration_shop";
        this.username = username;
        this.password = password;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        String query1 = "INSERT INTO transaction_details(transactionID, customerID, transaction_date) " +
                "VALUES (?, ?, ?)";
        String query2 = "INSERT INTO transaction_product(transactionID, productID, prize, quantity) " +
                "VALUES(?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst1 = con.prepareStatement(query1);
             PreparedStatement pst2 = con.prepareStatement(query2)) {

            pst1.setString(TP_TRANSACTIONID + 1, transaction.getTransactionID());
            pst1.setString(CUSTOMERID + 1, transaction.getCustomerID());
            pst1.setDate(TRANSACTION_DATE + 1, transaction.getTransactionDate());
            pst1.executeUpdate();

            for(Map.Entry<Product, Integer> entry : transaction.getTransactionItems().entrySet()) {
                pst2.setString(TD_TRANSACTIONID + 1, transaction.getTransactionID());
                pst2.setString(PRODUCTID + 1, entry.getKey().getProductID());
                pst2.setInt(PRICE + 1, entry.getKey().getPrice());
                pst2.setInt(QUANTITY + 1, entry.getValue());
                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public Transaction getTransaction(String transactionID) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public void deleteTransaction(String transactionID) {

    }
    // łączenie z bazą danych
    // pobieranie setu rekordów na podstawie: customerID
    // przyjmowanie listy obiektów (produktów biorących udział w transakcji) i dodawanie ich do bazy danych
}
