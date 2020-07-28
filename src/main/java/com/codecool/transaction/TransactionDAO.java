package com.codecool.transaction;

import com.codecool.transaction.Transaction;

import java.sql.Date;
import java.util.List;

public interface TransactionDAO {
    public void addTransaction(Transaction transaction);
    public Transaction getTransaction(String transactionID);
    public List<Transaction> getAllTransactionsByCustomer(String customerID);
    public List<Transaction> getAllTransactionsByDate(Date fromDate, Date toDate);
    public List<Transaction> getAllTransactions();
    public int deleteTransaction(String transactionID);

    // UPDATE
}
