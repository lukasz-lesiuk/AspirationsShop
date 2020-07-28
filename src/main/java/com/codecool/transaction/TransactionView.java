package com.codecool.transaction;

import com.codecool.view.basicView;

import java.util.List;
import java.util.Map;

public class TransactionView extends basicView {

    public void printTransaction(Transaction transaction) {
        Integer productIndex = 1;

        System.out.println(createExtremeHorizontal());
        System.out.println(createMainTextLine("CustomerID", transaction.getCustomerID()));
        System.out.println(createMainTextLine("TransactionID", transaction.getTransactionID()));
        System.out.println(createMainTextLine("Transaction date", transaction.getTransactionDate().toString()));
        System.out.println(createHorizontal());

        System.out.println(createHorizontal());
        System.out.format("|%2s|%10s|%6s|%9s|%7s|\n", " ", "(1)", "(2)", "(3)",
                "(2x3)");
        System.out.println(createHorizontal());
        System.out.format("|%2s|%10s|%6s|%9s|%7s|\n", " ", "ProductID", "Price", "Quantity",
                "Value");
        System.out.println(createHorizontal());

        for (Map.Entry<String, List<Integer>> entry : transaction.getTransactionItems().entrySet()) {
            String productID = entry.getKey();
            Integer price = entry.getValue().get(0);
            Integer quantity = entry.getValue().get(1);
            Integer value = price * quantity;

            System.out.format("|%2s|%10s|%6s|%9s|%7s|\n", productIndex, productID, price, quantity,
                    value);

            productIndex++;
        }
        System.out.println(createHorizontal());
        System.out.println(createMainTextLine("TOTAL VALUE", transaction.calculateTotalValue().toString()));
        System.out.println(createExtremeHorizontal());
    }

    private String createHorizontal() {
        StringBuilder newHorizontal = new StringBuilder();
        int[] width = {2, 10, 9, 6, 7};

        newHorizontal.append("+");

        for (int colWidth : width) {
            for (int i = 0; i < colWidth; i++) {
                newHorizontal.append("-");
            }
            newHorizontal.append("+");
        }

        return newHorizontal.toString();
    }

    private String createExtremeHorizontal() {
        StringBuilder topHorizontal = new StringBuilder();

        topHorizontal.append("+");
        for(int i = 0; i < 38; i++) {
            topHorizontal.append("-");
        }
        topHorizontal.append("+");

        return topHorizontal.toString();
    }

    private String createMainTextLine(String description, String value) {
        StringBuilder topLine = new StringBuilder();

        topLine.append("|");
        topLine.append(description + ": ");
        topLine.append(value);
        Integer filler = 38 - (description.length() + value.length());
        for(int i = 0; i < filler; i++) {
            topLine.append(" ");
        }
        topLine.append("|");

        return topLine.toString();
    }
}