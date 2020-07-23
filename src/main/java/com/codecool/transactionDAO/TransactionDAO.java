package com.codecool.transactionDAO;

import com.codecool.transaction.Transaction;

import java.util.List;

public interface TransactionDAO {
    void addNewTransactionRecord(List<Transaction> transactionRecords);
    // getAllTransactions() powinno zwracać listę składającą się z list transakcji powiązanych przez transactionID
    List<List<Transaction>> getAllTransactions();
    List<Transaction> getTransaction(String transactionID);
    void deleteTransaction(String transactionID);
}
