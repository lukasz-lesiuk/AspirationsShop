package com.codecool.shopping.search;

import com.codecool.product.Product;
import com.codecool.product.ProductDAO;
import com.codecool.product.ProductDAOPSQL;
import com.codecool.product.ProductView;
import com.codecool.shopping.basket.Basket;
import com.codecool.shopping.basket.BasketView;

import java.util.List;

public class SearchController {
    private Basket basket;
    private BasketView basketView = new BasketView();
    private List<Product> searchedPruducts;
    private ProductView productView = new ProductView();
    private ProductView view = new ProductView();
    private ProductDAO productDAO = new ProductDAOPSQL();

    public SearchController(Basket basket) {
        this.basket = basket;
    }

    public void run() {
        String phraseToSearch = "";

        while (!phraseToSearch.toLowerCase().equals("exit")) {
            phraseToSearch = getPhrase();
            searchedPruducts = productDAO.getProductByPhrase(phraseToSearch);
            view.printProductList(searchedPruducts);

            if (!searchedPruducts.isEmpty()) {
                Product selectedProduct = selectProductFromTheList(searchedPruducts);
                if (selectedProduct != null) {
                    menageProduct(selectedProduct);
                }
            } else if (searchedPruducts.isEmpty() && !phraseToSearch.toLowerCase().equals("exit")) {
                view.printMessage("Product not found.");
            }
        }
    }

    public String getPhrase() {
        view.clear();
        String phraseToSearch = "";
        while(phraseToSearch.equals("")) {
            view.printMessage("You can search for items in our store using the phrase you entered. \n");
            phraseToSearch = view.getTextInput("Enter a phrase to search ('exit' to exit): ");
        }

        return phraseToSearch;
    }

    public Product selectProductFromTheList(List<Product> listOfProducts) {
        String exit = "";
        Product selectedProduct = null;

        do {
            try {
                //view.printMessage("Select product from the list ('0' to exit)");
                int choice = view.getNumericInput("Select product from the list ('0' to exit)");
                boolean posistionIsOnTheList = choice >= 0 && choice -1 < listOfProducts.size();

                if (posistionIsOnTheList) {
                    selectedProduct = listOfProducts.get(choice -1);
                    exit = "exit";
                }
            } catch (IndexOutOfBoundsException e) {
                exit = "exit";
            } catch (NullPointerException e) {
                exit = "exit";
            }
        } while (!exit.equals("exit"));

        return selectedProduct;
    }

    public void menageProduct(Product selectedProduct) {
        int choice;
        boolean shouldRun = true;
        while (shouldRun) {
            // view.clear();
            view.printProduct(selectedProduct);
            view.printMessage("\n(1) Add product to basket");
            view.printMessage("(0) Back");
            choice = view.getNumericInput("Select an option");

            if (choice == 0) {
                shouldRun = false;
            } else if (choice == 1) {
                view.printMessage("Product quantity in stock: " + selectedProduct.getQuantity());
                int quantity = view.getNumericInput("Enter quantity of product you want to add");
                addToBasket(selectedProduct, quantity);
                view.clear();
                basketView.displayBasket(basket);
                view.pressEnter();
                shouldRun = false;
            } else {
                view.printMessage("There is no such option.");
            }
        }
    }

    private void addToBasket(Product selectedProduct, int quantity) {
        if (selectedProduct.checkQuantity(quantity)) {
            basket.addProduct(selectedProduct, quantity);
        } else {
            view.printMessage("There is not enough product in stock.");
        }
    }
}
