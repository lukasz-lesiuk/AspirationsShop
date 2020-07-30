package com.codecool.shopping.browse;

import com.codecool.product.Product;
import com.codecool.product.ProductDAO;
import com.codecool.product.ProductDAOPSQL;
import com.codecool.product.ProductView;
import com.codecool.shopping.basket.Basket;


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
    private ChooseController chooseController;

    public BrowseController(Basket basket){
        this.basket = basket;
    }

    public Basket run() {
        String choice;
        do {
            browseView.displayBrowseOptions();
            browseView.printMessage("(0) Back");
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    productList = getCategory(EnumCategory.Basic.getName());
                    productView.printProductList(productList);
                    browseView.printMessage("0. Back");

                    Product product = getChoosenProduct(productList);
                    productView.printProduct(product);

                    browseView.pressEnter();

                    Integer quantity = browseView.getNumericInput("Choose Qantity");

                    basket.addProduct(product, quantity);

                    break;
                case ("2"):
                    productList = getCategory(EnumCategory.Outdoor_Retreat.getName());
                    break;
                case ("3"):
                    productList = getCategory(EnumCategory.Seasons.getName());
                    break;
                case ("4"):
                    productList = getCategory(EnumCategory.Miscellaneous.getName());
                    break;
                case ("5"):
                    productList = getCategory(EnumCategory.Island_Living.getName());
                    break;
                case ("6"):
                    productList = getCategory(EnumCategory.Realm_of_Magic.getName());
                    break;
                case ("7"):
                    productList = getCategory(EnumCategory.Get_Famous.getName());
                    break;
                case ("8"):
                    productList = getCategory(EnumCategory.Potions.getName());
                    break;
                default:
                    if(!choice.equals("9")) {
                        browseView.printMessage("There is no such option. Select again.");
                    } // ocb?
            }
        } while (!choice.equals("0"));
        return basket;
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
}
