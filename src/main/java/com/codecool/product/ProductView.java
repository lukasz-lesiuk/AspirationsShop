package com.codecool.product;

import com.codecool.view.BasicView;

import java.util.List;

public class ProductView extends BasicView {
    public void printProductTableHeader(){
        System.out.println(createHorizontal());
        System.out.format("|%-30s|%-17s|%-8s|%-11s|\n", " Product Name", " Category", " Price",
                " Quantity");
        System.out.println(createHorizontal());
    }

    public void printProductContent(Product product){
        System.out.format("|%-30s|%-17s|%-8s|%-11s|\n", " " + product.getProductName()
            , " " + product.getCategory(), " " + product.getPrice(), " " + product.getQuantity());
        System.out.println(createHorizontal());

    }
    public void printProduct(Product product){
        System.out.println("\nProduct Name: " + product.getProductName());
        System.out.println("Category: " + product.getCategory());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("Description:");
        String description = product.getDescription();
        if(description.length()>80){
            String description_part1 = description.substring(0,80);
            String description_part2 = description.substring(80);
            System.out.println(description_part1);
            System.out.println(description_part2);
        }
        else{
            System.out.println(description);
        }
    }

    public void printProductList(List<Product> productList){
        printProductTableHeader();
        for(Product product : productList){
            printProductContent(product);
        }
    }
    private String createHorizontal() {
        StringBuilder newHorizontal = new StringBuilder();
        int[] width = {30, 17, 8, 11};

        newHorizontal.append("+");

        for (int colWidth : width) {
            for (int i = 0; i < colWidth; i++) {
                newHorizontal.append("-");
            }
            newHorizontal.append("+");
        }

        return newHorizontal.toString();
    }

}
