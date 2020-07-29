package com.codecool.shopping;

import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.shopping.basket.Basket;
import com.codecool.shopping.basket.BasketController;
import com.codecool.shopping.browse.Browse;
import com.codecool.transaction.SQLTransactionDAO;
import com.codecool.transaction.Transaction;
import com.codecool.transaction.TransactionView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class ShoppingController {
    ShoppingView view = new ShoppingView();
    TransactionView transView = new TransactionView();
    List<String> shoppingMenuOptions;
    Scanner scan = new Scanner(System.in);
    Customer activeCustomer;
    Basket basket;
    Browse browse;


    public ShoppingController(Customer activeCustomer) {
        prepareShoppingMenuOptions();
        this.activeCustomer = activeCustomer;
        this.basket = new Basket();
        this.browse = new Browse();
        //----------------------------------------------------------
        Product product = new Product("bs]c45Ae", "New item", 400, 10);
        basket.addProduct(product, 3);
        Product product1 = new Product("aa]c77re", "New item1", 200, 5);
        basket.addProduct(product1, 4);
        //----------------------------------------------------------
    }

    public void run() {
        String choice;
        do {
            view.displayShoppingMenu(shoppingMenuOptions);
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    // browse
                    break;
                case ("2"):
                    // search
                    break;
                case ("3"):
                    basket();
                    break;
                case ("4"):
                    SQLTransactionDAO SQLTransDAO = new SQLTransactionDAO();
//                    String customerID = activeCustomer.getCustomerId();
//                    List<Transaction> customersTransactions =
//                                    SQLTransDAO.getAllTransactionsByCustomer(customerID);
//                    if (!customersTransactions.isEmpty()) {
//                        for (Transaction transaction : customersTransactions) {
//                            transView.printTransaction(transaction);
//                        }
//                    } else {
//                        view.printMessage("You have no shopping history! Use our browser and create one! :)");
//                    }
                    java.sql.Date from = Date.valueOf("2020-07-29");
                    java.sql.Date to = Date.valueOf("2020-07-29");
                    List<Transaction> transactionList =
                            SQLTransDAO.getAllTransactionsByDate(from, to);
                    int index = 1;
                    for(Transaction transaction : transactionList) {
                        view.printMessage("\nTransaction number " + index + ":");
                        transView.printTransaction(transaction);
                        index++;
                    }
                    break;
                case ("5"):
                    if (!basket.getTransactionProducts().isEmpty()) {
                        String exitDecision = view.getTextInput("Your basket is not empty. Do you want to leave anyway? y/n");
                        if (exitDecision.toLowerCase().equals("y")) {
                            choice = "EXIT";
                        }
                    }
                default:
                    if(!choice.equals("5")) {
                        view.printMessage("There is no such option. Select again.");
                    }
            }
        } while (!choice.equals("EXIT"));
    }

    private void prepareShoppingMenuOptions() {
        this.shoppingMenuOptions = new ArrayList<>();
        shoppingMenuOptions.add("Browse products");
        shoppingMenuOptions.add("Search products");
        shoppingMenuOptions.add("Your basket");
        shoppingMenuOptions.add("Shopping history");
        shoppingMenuOptions.add("Logout");
    }

    private void basket() {
        BasketController basketController = new BasketController(basket, activeCustomer);
        basketController.run();
    }
}
