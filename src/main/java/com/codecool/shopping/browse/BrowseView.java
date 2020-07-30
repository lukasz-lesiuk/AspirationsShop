package com.codecool.shopping.browse;

import com.codecool.view.basicView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowseView extends basicView {



    public void displayBrowseOptions() {
        List<String> menuOptions = getBrowseCategory();
        clear();
        printOptions(menuOptions, "Choose category:");
    }

    private List<String> getBrowseCategory(){
        List<String> browseCategory = new ArrayList<>();

        List<EnumCategory> categoryList = Arrays.asList(EnumCategory.values());
        for( EnumCategory category : categoryList){
            browseCategory.add(category.getName());
        }
        return browseCategory;
    }
}
