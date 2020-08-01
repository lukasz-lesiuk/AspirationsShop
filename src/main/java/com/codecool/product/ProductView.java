package com.codecool.product;

import com.codecool.view.basicView;

import java.util.List;
import java.util.Map;

public class ProductView extends basicView {
    public void printProductTableHeader(){
        System.out.println(createHorizontal());
        System.out.format("|%-30s|%-17s|%-8s|%-11s|\n", " Product Name", " Category", " Price",
                " Quantity");
        System.out.println(createHorizontal());
    }

    public void printProductContent(Product product){System.out.format("|%-30s|%-17s|%-8s|%-11s|\n", " " + product.getProductName()
            , " " + product.getCategory(), " " + product.getPrice(), " " + product.getQuantity());
        System.out.println(createHorizontal());

    }
    public void printProduct(Product product){
    printProductTableHeader();
    printProductContent(product);
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
