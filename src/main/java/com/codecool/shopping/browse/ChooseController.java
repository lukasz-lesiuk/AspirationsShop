package com.codecool.shopping.browse;

import com.codecool.product.Product;
import com.codecool.shopping.basket.Basket;
import com.codecool.view.BasicView;

import java.util.List;

public class ChooseController {
    private List<Product> productList;
    private Basket basket = new Basket();
    private BasicView chooseControllerView = new BrowseView();
    private Product product;


    ChooseController(List<Product> productList){
        this.productList = productList;
    }

    public Product chooseProduct() {
        String exit= "";
        product = null;

        do {
            try{
            Integer choice = chooseControllerView.getNumericInput("Choose product");
            boolean positionIsONList = choice >= 0 &&  choice -1 <productList.size();

            if (positionIsONList){
                product = getProduct(choice -1);
                exit = "EXIT"; }
            }
            catch (IndexOutOfBoundsException e){
                exit = "EXIT";
            }
            catch (NullPointerException e){
                exit = "EXIT";
            }
        }while (!exit.equals("EXIT"));

        return product;
    }

    private Product getProduct(int position){
        return productList.get(position);
    }
}
