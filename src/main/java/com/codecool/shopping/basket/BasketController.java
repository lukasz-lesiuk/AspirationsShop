package com.codecool.shopping.basket;

import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BasketController {
    BasketView view = new BasketView();
    List<String> options;
    Basket basket;
    Customer activeCustomer;

    public BasketController(Basket basket, Customer activeCustomer){
        this.basket = basket;
        this.activeCustomer = activeCustomer;
        this.options = new ArrayList<>();
    }

    public void run() {
        prepareMenu();
        String choice;
        do {
            view.clear();
            view.displayBasket(basket);
            view.printMessage("\n");
            view.printSubmenu(options, "Basket menu ");
            choice = view.input("");

            switch (choice){
                case ("1"):
                    view.clear();
                    view.displayBasket(basket);
                    if (!basket.getTransactionProducts().isEmpty()) {
                        int productChoice = basketProductSelection("Provide number of product you want to modify");
                        Product chosenProduct = basket.getProduct(productChoice - 1);
                        int newQuantity = changeQuantity(chosenProduct);

                        basket.getTransactionProducts().put(chosenProduct, newQuantity);
                    }
                    break;
                case ("2"):
                    view.clear();
                    view.displayBasket(basket);
                    if (!basket.getTransactionProducts().isEmpty()) {
                        int productChoice = basketProductSelection("Provide number of product you want to modify");
                        Product chosenProduct = basket.getProduct(productChoice - 1);

                        basket.removeProductFromBasket(chosenProduct);
                    }
                    break;
                case ("3"):
                    view.clear();
                    basket.clearBasket();
                    break;
                case ("4"):
                    view.clear();
                    view.displayBasket(basket);
                    // do you want to complete order?
                    Purchase purchase = new Purchase(basket, activeCustomer);
                    basket.clearBasket();
                    Transaction newTransaction = purchase.finalizeTransaction();
                    // productDAO dostanie purchase.productsToUpdateInWarehouse
                    // print: order completed
                    choice = "5";
                    break;
                default:
                    if (!choice.equals("5")) {
                        view.printMessage("There is no such option. Select again.");
                    }
            }
        } while (!choice.equals("5"));
    }

    private void prepareMenu() {
        this.options = new ArrayList<>();
        options.add(" Change quantity of product");
        options.add(" Delete product");
        options.add(" Clear your basket");
        options.add(" Complete the order");
        options.add(" Back");
    }

    private int basketProductSelection(String message) {
        int productChoice = 0;
        while(((productChoice > 0) && (productChoice <= basket.getTransactionProducts().size())) == false) {
            productChoice = view.getNumericInput(message);
            if((productChoice > 0) && (productChoice <= basket.getTransactionProducts().size()) == false) {
                view.printMessage("Provide valid number from the list.");
            }
        }
        return productChoice;
    }

    private int changeQuantity(Product productToChange) {
        int newQuantity = 0;
        view.printMessage("Maximum quantity of product: " + productToChange.getQuantity());
        while (((newQuantity > 0) && (newQuantity <= productToChange.getQuantity())) == false) {
            newQuantity = view.getNumericInput("Provide new quantity");
            if (((newQuantity > 0) && (newQuantity <= productToChange.getQuantity())) == false) {
                view.printMessage("No required quantity in stock.");
            }
        }
        return newQuantity;
    }
}