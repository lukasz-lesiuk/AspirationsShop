package com.codecool.shopping.basket;

import com.codecool.product.Product;
import com.codecool.view.basicView;

import java.util.*;

public class BasketView extends basicView {

    Scanner scan = new Scanner(System.in);

    public void displayBasket(Basket basket) {
        if (!basket.getTransactionProducts().isEmpty()) {
            int listIndex = 1;

            System.out.println(createHorizontal());
            System.out.format("|%2s|%25s|%6s|%9s|%7s|\n", " ", "Product name", "Price", "Quantity",
                    "P x Q");
            System.out.println(createHorizontal());

            for (Map.Entry<Product, Integer> entry : basket.getTransactionProducts().entrySet()) {
                String productName = entry.getKey().getProductName();
                Integer price = entry.getKey().getPrice();
                Integer quantity = entry.getValue();
                Integer total = price * quantity;

                System.out.format("|%2s|%25s|%6s|%9s|%7s|\n", listIndex, productName, price, quantity,
                        total);
                System.out.println(createHorizontal());

                listIndex++;
            }
            System.out.println(createSingleTextLine("BASKET TOTAL VALUE",
                                                     Integer.toString(basket.getTotalBasketValue())));
            System.out.println(createExtremeHorizontal());
        } else {
                printMessage("You basket is empty. Use our browser to add products");
        }
    }

    private String createHorizontal() {
        StringBuilder newHorizontal = new StringBuilder();
        int[] width = {2, 25, 6, 9, 7};

        newHorizontal.append("+");

        for (int colWidth : width) {
            for (int i = 0; i < colWidth; i++) {
                newHorizontal.append("-");
            }
            newHorizontal.append("+");
        }

        return newHorizontal.toString();
    }

    private String createSingleTextLine(String description, String value) {
        StringBuilder line = new StringBuilder();

        line.append("|");
        Integer filler = 53 - (description.length() + value.length() + 2);
        for(int i = 0; i < filler; i++) {
            line.append(" ");
        }
        line.append(description + ": ");
        line.append(value);
        line.append("|");

        return line.toString();
    }

    private String createExtremeHorizontal() {
        StringBuilder topHorizontal = new StringBuilder();

        topHorizontal.append("+");
        for(int i = 0; i < 53; i++) {
            topHorizontal.append("-");
        }
        topHorizontal.append("+");

        return topHorizontal.toString();
    }

}
