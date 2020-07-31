package com.codecool.shopping.search;

import com.codecool.product.Product;
import com.codecool.product.ProductDAO;
import com.codecool.product.ProductDAOPSQL;
import com.codecool.product.ProductView;
import com.codecool.shopping.basket.Basket;

import java.util.List;

public class SearchController {
    private Basket basket;
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

            if (!searchedPruducts.isEmpty()) {
                Product selectedProduct = selectProductFromTheList(searchedPruducts);
                if (!selectedProduct.equals(null)) {
                    menageProduct(selectedProduct);
                }
            } else if (searchedPruducts.isEmpty() && !phraseToSearch.toLowerCase().equals("exit")) {
                view.printMessage("Product not found.");
            }
        }
    }

    public String getPhrase() {
        view.clear();
        view.printMessage("You can search for items in our store using the phrase you entered. \n");
        String phraseToSearch = view.getTextInput("Enter a phrase to search ('exit' to exit): ");

        return phraseToSearch;
    }

    public Product selectProductFromTheList(List<Product> listOfProducts) {
        boolean shouldRun = true;
        Product selectedProduct = null;

        while (shouldRun) {
            view.clear();
            productView.printProductList(searchedPruducts);
            int choice = menageList(searchedPruducts);

            if (choice > 0 && choice <= searchedPruducts.size()) {
                selectedProduct = searchedPruducts.get(choice - 1);
                shouldRun = false;
            } else if (choice == 0) {
                shouldRun = false;
            } else {
                view.printMessage("Wrong choice, try again");
            }
        }

        return selectedProduct;
    }

    public void menageProduct(Product selectedProduct) {
        int choice;
        boolean shouldRun = true;
        while (shouldRun) {
            view.clear();
            view.printProduct(selectedProduct);
            view.printMessage("(1) Add product to basket");
            view.printMessage("(0) Back");
            choice = view.getNumericInput("");

            if (choice == 0) {
                shouldRun = false;
            } else if (choice == 1) {
                view.printMessage("Product quantity in stock: " + selectedProduct.getQuantity());
                int quantity = view.getNumericInput("Enter quantity of product you want to add");
                addToBasket(selectedProduct, quantity);
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

    private int menageList(List<Product> listOfProducts) {
        int choice;
        do {
            choice = view.getNumericInput("Select the number of product from the list ('0' to exit)");
        } while (!(choice >= 0 && choice <= listOfProducts.size()));

        return choice;
    }
}
