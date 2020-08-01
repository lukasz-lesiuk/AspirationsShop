package com.codecool.product;

import com.codecool.view.basicView;

import java.util.List;
import java.util.Map;

public class ProductView extends basicView {
    public void printProductTableHeader(int [] width){
        System.out.println(createHorizontal(width));
        String format = "|%-"+width[0]+"s|%-"+width[1]+"s|%-"+width[2]+"s|%-"+width[3]+"s|%-"+width[4]+"s|\n";
        System.out.format(format,"Id", " Product Name", " Category", " Price",
                " Quantity");
        System.out.println(createHorizontal(width));
    }

    public void printProductContent(Product product, int [] width){
        String format = "|%-"+width[0]+"s|%-"+width[1]+"s|%-"+width[2]+"s|%-"+width[3]+"s|%-"+width[4]+"s|\n";
        System.out.format(format," " + product.getProductID(), " " + product.getProductName()
            , " " + product.getCategory(), " " + product.getPrice(), " " + product.getQuantity());
        System.out.println(createHorizontal(width));

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
        int [] width = checkColumnWidth(productList);
        printProductTableHeader(width);
        for(Product product : productList){
            printProductContent(product,width);
        }
    }
    private String createHorizontal(int [] width) {
        StringBuilder newHorizontal = new StringBuilder();

        newHorizontal.append("+");

        for (int colWidth : width) {
            for (int i = 0; i < colWidth; i++) {
                newHorizontal.append("-");
            }
            newHorizontal.append("+");
        }

        return newHorizontal.toString();
    }

    private int[] checkColumnWidth(List<Product> products) {
        int idWidth = 4;
        int nameWidth = 14;
        int categoryWidth = 10;
        int priceWidth = 7;
        int quantityWidth = 11;
        for(Product product : products) {
            if(idWidth < String.valueOf(product.getProductID()).length()) idWidth = String.valueOf(product.getProductID()).length() + 1;
            if(nameWidth < product.getProductName().length()) nameWidth = product.getProductName().length() + 3;
            if(categoryWidth<product.getCategory().length()) categoryWidth = product.getCategory().length() + 1;
            if(priceWidth < String.valueOf(product.getPrice()).length()) priceWidth = String.valueOf(product.getPrice()).length() + 1;
            if(quantityWidth < String.valueOf(product.getQuantity()).length()) quantityWidth = String.valueOf(product.getQuantity()).length() + 1;
        }
            int [] width = {idWidth,nameWidth,categoryWidth,priceWidth,quantityWidth};
            return width;
        }


    }

