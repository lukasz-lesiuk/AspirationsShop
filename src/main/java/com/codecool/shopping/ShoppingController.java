package com.codecool.shopping;

import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.product.ProductDAO;
import com.codecool.product.ProductDAOPSQL;
import com.codecool.product.ProductView;
import com.codecool.shopping.basket.Basket;
import com.codecool.shopping.basket.BasketController;
import com.codecool.shopping.browse.Browse;
import com.codecool.shopping.browse.BrowseController;
import com.codecool.shopping.browse.EnumCategory;
import com.codecool.shopping.search.SearchController;
import com.codecool.transaction.SQLTransactionDAO;
import com.codecool.transaction.Transaction;
import com.codecool.transaction.TransactionDAO;
import com.codecool.transaction.TransactionView;

import java.util.*;
import java.sql.Date;

public class ShoppingController {
    private ShoppingView view = new ShoppingView();
    private TransactionView transView = new TransactionView();
    private List<String> shoppingMenuOptions;
    private Scanner scan = new Scanner(System.in);
    private Customer activeCustomer;
    private Basket basket;
    private Browse browse;
    private BrowseController browseController;


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
                    browse();
                    break;
                case ("2"):
                    search();
                    break;
                case ("3"):
                    basket();
                    break;
                case ("4"):
                    getTransactionHitoryByCust();
                    break;
                case ("5"):
                    choice = exit();
                    break;
                default:
                    if(!choice.equals("5")) {
                        view.printMessage("There is no such option. Select again.");
                    }
            }
        } while (!choice.equals("EXIT"));
    }

    private void getTransactionHitoryByCust() {
        TransactionDAO SQLTransDAO = new SQLTransactionDAO();
        String customerID = activeCustomer.getCustomerId();
        List<Transaction> customersTransactions =
                        SQLTransDAO.getAllTransactionsByCustomer(customerID);
        if (!customersTransactions.isEmpty()) {
            for (Transaction transaction : customersTransactions) {
                transView.printTransaction(transaction);
            }
        } else {
            view.printMessage("You have no shopping history! Use our browser and create one! :)");
        }
        transView.printTransactions(customersTransactions);
    }

    private String exit() {
        String choice = "";
        if (!basket.getTransactionProducts().isEmpty()) {
            String exitDecision = view.getTextInput("Your basket is not empty. Do you want to leave anyway? y/n");
            if (exitDecision.toLowerCase().equals("y")) {
                choice = "EXIT";
            }
        } else {
            choice = "EXIT";
        }
        return choice;
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

    private void browse() {
        browseController = new BrowseController(basket);
        browseController.run();
    }

    private void search() {
        SearchController searchController = new SearchController(basket);
        searchController.run();
    }

//    private void getTransactionByDate(){
//        //to used by customer controller
//        TransactionDAO SQLTransDAO = new SQLTransactionDAO();
//
//        java.sql.Date from = Date.valueOf("2020-12-01");
//        java.sql.Date to = Date.valueOf("2020-12-01");
//        List<Transaction> transactionList =
//                SQLTransDAO.getAllTransactionsByDate(from, to);
//        transView.printTransactions(transactionList);
//    }

    private void updateBasket(Basket basket){
        for (Map.Entry<Product, Integer> entry : basket.getTransactionProducts().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            this.basket.addProduct(product, quantity);
        }
    }
}
