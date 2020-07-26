package com.codecool.shopping;

import com.codecool.transaction.Product;
import com.codecool.view.basicView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BasketView extends basicView {

    Scanner scan = new Scanner(System.in);

    public void viewAllProducts(Map<Product, Integer> allProducktsInBasket){
        if (!allProducktsInBasket.isEmpty()) {
            int productIndex = 1;
            for (Map.Entry<Product, Integer> entry : allProducktsInBasket.entrySet()) {
                String productName = entry.getKey().getProductName();
                Integer price = entry.getKey().getPrice();
                Integer quantity = entry.getValue();
                Integer value = price * quantity;

                System.out.format("|%2s|%15s|%6s|%9s|%7s|\n", productIndex, productName, price, quantity,
                        value);

                productIndex++;
            }
        }
        else{
                printMessage("You basket is empty. Use our broser to add products");
            }
    }


    public String input(String message){
        System.out.print(message + ": ");
        String input = scan.nextLine();
        clear();
        return input;
    }
}
