package com.codecool.app;

import com.codecool.access.RegistrationController;
import com.codecool.customer.Customer;
import com.codecool.shopping.ShoppingController;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.employeeController.EmployeeController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RootController {
    RootView view = new RootView();
    Scanner scan = new Scanner(System.in);
    List<String> options;
    Customer customer = new Customer("asdfasef", "Krzysiek", "Chromiec", "12414141",
            "asdfasdf@asdf.pl", "Krakow", "Mlynska", "asdfasdfasef");

    public void run() {
        prepareMenu();
        String choice;
        do {
            view.displayMainMenu(options);
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    login();
                    break;
                case ("2"):
                    register();
                    break;
                case ("3"):
                    // You can add any function you wish to test here
                    EmployeeController ec = new EmployeeController();
                    ec.run();

                    break;
                case ("4"):
                    shopping();
                    break;
                case ("okon"):
                    // place to hide a 'secret' functionality
                    break;
                default:
                    view.addMessage("Option not on the list.");
            }
        } while (!choice.equals("0"));
    }

    private void prepareMenu() {
        this.options = new ArrayList<>();
        options.add("Login");
        options.add("Register");
        options.add("Option");
        options.add("Shopping menu");
    }

    private void login() {
        // TODO to be implemented
    }

    private void register() {
        RegistrationController regCon = new RegistrationController();
        regCon.run();
    }

    private void shopping() {
        ShoppingController shopCon = new ShoppingController(customer);
        shopCon.run();
    }
}
