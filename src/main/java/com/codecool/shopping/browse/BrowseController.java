package com.codecool.shopping.browse;

import com.codecool.product.Product;
import com.codecool.product.ProductDAO;
import com.codecool.product.ProductDAOPSQL;
import com.codecool.product.ProductView;
import com.codecool.shopping.basket.Basket;
import com.codecool.shopping.basket.BasketView;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BrowseController {

    private BrowseView browseView = new BrowseView();
    private Scanner scan = new Scanner(System.in);
    private ProductView productView = new ProductView();
    private List<Product> productList = new ArrayList<>();
    private ProductDAO productDAO = new ProductDAOPSQL();
    private Basket basket;
    private BasketView basketView = new BasketView();
    private ChooseController chooseController;

    public BrowseController(Basket basket){
        this.basket = basket;
    }

    public void run() {
        String choice;
        do {
            browseView.displayBrowseOptions();
            browseView.printMessage("(0) Back");
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    productList = getCategory(EnumCategory.Basic.getName());
                    getProductByCat(productList);
                    break;

                case ("2"):
                    productList = getCategory(EnumCategory.Outdoor_Retreat.getName());
                    getProductByCat(productList);
                    break;

                case ("3"):
                    productList = getCategory(EnumCategory.Seasons.getName());
                    getProductByCat(productList);
                    break;

                case ("4"):
                    productList = getCategory(EnumCategory.Miscellaneous.getName());
                    getProductByCat(productList);
                    break;

                case ("5"):
                    productList = getCategory(EnumCategory.Island_Living.getName());
                    getProductByCat(productList);
                    break;

                case ("6"):
                    productList = getCategory(EnumCategory.Realm_of_Magic.getName());
                    getProductByCat(productList);
                    break;

                case ("7"):
                    productList = getCategory(EnumCategory.Get_Famous.getName());
                    getProductByCat(productList);
                    break;

                case ("8"):
                    productList = getCategory(EnumCategory.Potions.getName());
                    getProductByCat(productList);
                    break;

                default:
                    if(!choice.equals("9")) {
                        browseView.printMessage("There is no such option. Select again.");
                    }
            }
        } while (!choice.equals("0"));
    }

    private void getProductByCat(List<Product> productList) {
        productView.printProductList(productList);
        String decision = "";
        Product product = getChoosenProduct(productList);
        if (product != null) {
            productView.printProduct(product);
            decision = browseView.getTextInput("\nAdd to basket? y/n");
            if(decision.toLowerCase().equals("y")){
                Integer quantity = askForQuantity(product);
                basket.addProduct(product, quantity);
                basketView.clear();
                basketView.displayBasket(basket);
                browseView.pressEnter();
            }
        }
    }

    private Integer askForQuantity(Product product){
        boolean isEnoughtProduct = false;
        Integer quantity;
        do {
            quantity = browseView.getNumericInput("Choose quantity");
            isEnoughtProduct = checkWearhouse(product, quantity);

        } while (!isEnoughtProduct);

        return quantity;
    }

    private List<Product> getCategory(String category){
        browseView.clear();
        productList = productDAO.getProductByCategory(category);
        return productList;
    }

    private Product getChoosenProduct(List<Product> productList){
        chooseController = new ChooseController(productList);
        return chooseController.chooseProduct();
    }

    private boolean checkWearhouse(Product selectedProduct, int quantity) {
        if (selectedProduct.checkQuantity(quantity)) {
            return true;
        } else {
            browseView.printMessage("There is not enough product in stock.");
            return false;
        }
    }

}
