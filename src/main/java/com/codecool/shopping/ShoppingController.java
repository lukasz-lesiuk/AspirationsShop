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
                    String customerID = activeCustomer.getCustomerId();
                    List<Transaction> customersTransactions =
                                    SQLTransDAO.getAllTransactionsByCustomer(customerID);
                    for(Transaction transaction : customersTransactions) {
                        transView.printTransaction(transaction);
                    }
                    break;
                case ("5"):
                    // logout
                default:
                    if(!choice.equals("5")) {
                        view.printMessage("There is no such option. Select again.");
                    }
            }
        } while (!choice.equals("5"));
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
