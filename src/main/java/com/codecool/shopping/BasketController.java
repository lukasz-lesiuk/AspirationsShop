package com.codecool.shopping;

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
            view.viewAllProducts(basket.getTransactionProducts());

            view.printOptions(options, "CHOSE OPTION: ");
            choice = view.input("");

            switch (choice){
                case ("1"):
                    view.clear();
                    view.viewAllProducts(basket.getTransactionProducts());
                    break;
                case ("2"):
                    view.clear();
                    view.viewAllProducts(basket.getTransactionProducts());
                    view.printMessage("Choose product to delete from basket");
                    choice = view.input("");
                    if (basket.getTransactionProducts().containsKey(choice))
                        {
                            basket.deleteProduct(basket.getProduct(choice));
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