package com.codecool.shopping;

import com.codecool.product.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketController {

    BasketView view = new BasketView();
    List<String> options;
    Basket basket;

    BasketController(Basket basket){
        this.basket = basket;
        this.options = new ArrayList<>();
    }

    public void run() {
        prepareMenu();
        String choice;

        do {
            view.displayAllProducts(basket.getTransactionProducts());

            view.printOptions(options, "CHOSE OPTION: ");
            choice = view.input("");

            switch (choice){
                case ("1"):
                    view.clear();
                    view.displayAllProducts(basket.getTransactionProducts());
                    Integer chosenProductToUpdate = Integer.valueOf(view.input("Choose product to update. "));
                    if (chosenProductToUpdate > 0 && chosenProductToUpdate < basket.getTransactionProducts().size()) {
                        Product product = basket.getProduct(chosenProductToUpdate);
                        Integer quantity = Integer.valueOf(view.input("Type new quantity. "));
                        if (quantity > 0) {
                            basket.addProduct(product, quantity);
                        } else {view.printMessage("Quantity must be possitive number. ");}
                        if (!basket.checkInventory(product, quantity)){
                            view.printMessage("No required quantity in stock. ");
                        }
                    } else {view.printMessage("Wrong input! Try again. ");};

                    break;
                case ("2"):
                    view.clear();
                    view.displayAllProducts(basket.getTransactionProducts());
                    Integer chosenProductToDelete = Integer.valueOf(view.input("Choose product to delete from basket."));

                    if (chosenProductToDelete > 0 && chosenProductToDelete < basket.getTransactionProducts().size()) {
                        basket.deleteProduct(basket.getProduct(chosenProductToDelete));
                    }
                    break;
                case ("3"):
                    view.clear();
                    basket.clearBasket();
                    break;
                case ("4"):

                    break;
                default:
                    view.printMessage("There is no such option. Select again.");
            }
        } while (!choice.equals("5"));
    }

    private void prepareMenu() {
        this.options = new ArrayList<>();
        options.add(" Change quantity of product");
        options.add(" Delete product");
        options.add(" Clear your basket");
        options.add(" Finalise you basket");
        options.add(" Back");
    }

}