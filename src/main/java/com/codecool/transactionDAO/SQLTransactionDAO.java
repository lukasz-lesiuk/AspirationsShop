package com.codecool.transactionDAO;

import com.codecool.transaction.Transaction;

import java.util.List;

public class SQLTransactionDAO implements TransactionDAO {
    @Override
    public void addTransaction(Transaction transaction) {

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
