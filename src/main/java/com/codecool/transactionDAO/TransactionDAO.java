package com.codecool.transactionDAO;

import com.codecool.transaction.Transaction;

import java.util.List;

public interface TransactionDAO {
    public void addTransaction(Transaction transaction);
    public Transaction getTransaction(String transactionID);
    public List<Transaction> getAllTransactions();
    public void deleteTransaction(String transactionID);
    // UPDATE
}
