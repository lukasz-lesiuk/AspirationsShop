package com.codecool.shopping;

import com.codecool.shopping.browse.BrowseCategory;
import com.codecool.view.basicView;

import java.util.List;

public class ShoppingView extends basicView {

    void displayShoppingMenu(List<String> menuOptions) {
        clear();
        printOptions(menuOptions, "Shopping menu:");
    }

    void displayCategoriesEnum() {
        BrowseCategory[] categories = BrowseCategory.values();

        Integer i = 1;
        for(Enum enumElement : categories) {
            System.out.format("(%s) %s,", i, enumElement.toString());
            i++;
        }
        printMessage("(0) Back");
    }
}
