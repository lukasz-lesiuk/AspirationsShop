package com.codecool.shopping;

import com.codecool.product.Product;
import com.codecool.view.basicView;

import java.util.*;

public class BasketView extends basicView {

    Scanner scan = new Scanner(System.in);

    public void viewAllProducts(Map<Product, Integer> allProductsInBasket) {
        List<Product> listOfProducts = createProductSet(allProductsInBasket);

        if (!allProductsInBasket.isEmpty()) {
            int productIndex = 1;
            for (Product product : listOfProducts) {
                String productName = product.getProductName();
                Integer price = product.getPrice();
                Integer quantity = allProductsInBasket.get(product);
                Integer value = price * quantity;

                System.out.format("|%2s|%15s|%6s|%9s|%7s|\n", productIndex, productName, price, quantity,
                        value);

                productIndex++;
            }
        }
        else{
                printMessage("You basket is empty. Use our browser to add products");
            }
    }

    private List<Product> createProductSet(Map<Product, Integer> allProductsInBasket) {
        Set<Product> productsSet = allProductsInBasket.keySet();
        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.addAll(productsSet);

        return listOfProducts;
    }

    public String input(String message){
        System.out.print(message + ": ");
        String input = scan.nextLine();
        clear();
        return input;
    }
}
