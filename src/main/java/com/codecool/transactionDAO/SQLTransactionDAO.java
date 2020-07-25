package com.codecool.transactionDAO;

import com.codecool.transaction.Product;
import com.codecool.transaction.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

            for(Map.Entry<String, List<Integer>> entry : transaction.getTransactionItems().entrySet()) {
                int price = entry.getValue().get(0);
                int quantity = entry.getValue().get(1);
                pst2.setString(TD_TRANSACTIONID + 1, transaction.getTransactionID());
                pst2.setString(PRODUCTID + 1, entry.getKey());
                pst2.setInt(PRICE + 1, price);
                pst2.setInt(QUANTITY + 1, quantity);
                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public Transaction getTransaction(String transactionID) {
        String query1 = "SELECT * FROM transaction_details WHERE transactionID = '?' ;";
        String query2 = "SELECT * FROM transaction_product WHERE transactionID = '?' ;";
        
        Transaction transaction = null;

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst1 = con.prepareStatement(query1);
             PreparedStatement pst2 = con.prepareStatement(query2)) {

             pst1.setString(TP_TRANSACTIONID + 1, transactionID);
             pst2.setString(TP_TRANSACTIONID + 1, transactionID);
             ResultSet rs1 = pst1.executeQuery();
             ResultSet rs2 = pst2.executeQuery();

             String transaction_ID = rs1.getString("transactionID");
             String customerID = rs1.getString("customerID");
             Date transaction_date = rs1.getDate("transaction_date");

             HashMap<String, List<Integer>> transactionItems = new HashMap<>();

             while (rs2.next()){
                String productID = rs1.getString("productID");
                Integer price = rs1.getInt("prize");
                Integer quantity = rs1.getInt("quantity");

                List<Integer> value = new ArrayList<>();
                value.add(price);
                value.add(quantity);

                transactionItems.put(productID, value);
            }

            transaction = new Transaction(transaction_ID, customerID, transactionItems, transaction_date);

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return transaction;
    }

        @Override
    public List<Transaction> getAllTransactionsByCustomer(String customerID) {
        String query = "SELECT * FROM transaction_details WHERE customerID = '?';";

        Integer Customer_ID = 0;
        List<Transaction> transactionsListByCustomer = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(Customer_ID + 1, customerID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String transaction_ID = rs.getString("transactionID");
                Transaction transaction = getTransaction(transaction_ID);
                transactionsListByCustomer.add(transaction);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return transactionsListByCustomer;
    }

//    @Override
//    //!!! casting date!!!
//    public List<Transaction> getAllTransactionsByDate(java.util.Date fromDate, java.util.Date toDate){
//
//        String query = "SELECT * FROM transaction_details WHERE transaction_date > '?' AND " +
//                "WHERE transaction_date < '?';";
//
//        Integer from_Date = 0;
//        Integer to_Date = 0;
//
//        List<Transaction> transactionsListByCustomer = new ArrayList<>();
//
//        try (Connection con = DriverManager.getConnection(url, username, password);
//             PreparedStatement pst = con.prepareStatement(query)) {
//
//            pst.setDate(from_Date + 1, fromDate);
//            pst.setDate(from_Date + 1, toDate);
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()){
//                String transaction_ID = rs.getString("transactionID");
//                Transaction transaction = getTransaction(transaction_ID);
//                transactionsListByCustomer.add(transaction);
//            }
//
//        } catch (SQLException ex) {
//            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
//            lgr.log(Level.SEVERE, ex.getMessage(), ex);
//        }
//        return transactionsListByCustomer;
//    }

    @Override
    public List<Transaction> getAllTransactions(){
        String query = "SELECT * FROM transaction_details";

        List<Transaction> transactionsListByCustomer = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String transaction_ID = rs.getString("transactionID");
                Transaction transaction = getTransaction(transaction_ID);
                transactionsListByCustomer.add(transaction);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return transactionsListByCustomer;
    }


    @Override
    public int deleteTransaction(String transactionID) {
        String query = "DELETE FROM transaction_details WHERE transactionID = '?';";

        int affectrows= 0;

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst1 = con.prepareStatement(query)) {

            pst1.setString(TP_TRANSACTIONID + 1, transactionID);

            affectrows = pst1.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  affectrows;
    }
}


