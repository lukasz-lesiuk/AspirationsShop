package com.codecool.shopping.basket;

import com.codecool.customer.Customer;
import com.codecool.product.Product;
import com.codecool.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BasketController {
    private BasketView view = new BasketView();
    private List<String> options;
    private Basket basket;
    private Customer activeCustomer;

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
            choice = view.getTextInput("");

            switch (choice) {
                case ("1"):
                    changeBasketProductsQuantity();
                    break;
                case ("2"):
                    deleteProductFromBasket();
                    break;
                case ("3"):
                    choice = clearBasket();
                    break;
                case ("4"):
                    choice = finalizeBasket();
                    break;
                default:
                    if (!choice.equals("5")) {
                        view.printMessage("There is no such option. Select again.");
                    }
            }
        } while (!choice.equals("5"));
    }

    private String finalizeBasket() {
        String choice = "";
        view.clear();
        view.displayBasket(basket);
        String completeOrderDecision = view.getTextInput("Do you want to finalize you order? y/n");
        if (completeOrderDecision.toLowerCase().equals("y")) {
            Purchase purchase = new Purchase(basket, activeCustomer);
            purchase.finalizeTransaction();
            basket.clearBasket();

            choice = "5";
        }
        return choice;
    }

    private String clearBasket() {
        String choice = "";
        view.clear();
        view.displayBasket(basket);
        String clearDecision = view.getTextInput("Do you want to remove all products from your basket? y/n");
        if (clearDecision.toLowerCase().equals("y")) {
            basket.clearBasket();
            choice = "5";
        }
        return choice;
    }

    private void deleteProductFromBasket() {
        view.clear();
        view.displayBasket(basket);
        if (!basket.getTransactionProducts().isEmpty()) {
            int productChoice = basketProductSelection("Provide number of product you want to modify");
            Product chosenProduct = basket.getProduct(productChoice - 1);

            basket.removeProductFromBasket(chosenProduct);
        }
    }

    private void changeBasketProductsQuantity() {
        view.clear();
        view.displayBasket(basket);
        if (!basket.getTransactionProducts().isEmpty()) {
            int productChoice = basketProductSelection("Provide number of product you want to modify");
            Product chosenProduct = basket.getProduct(productChoice - 1);
            int newQuantity = changeQuantity(chosenProduct);

            basket.getTransactionProducts().put(chosenProduct, newQuantity);
        }
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