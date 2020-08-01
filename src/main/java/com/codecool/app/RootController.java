package com.codecool.app;

import com.codecool.access.AdminLoginController;
import com.codecool.access.LoginController;
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
    AdminLoginController adminLogger = new AdminLoginController();

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
                    boolean shouldLogIn = adminLogger.checkAdminPassword();
                    if (shouldLogIn) {
                        EmployeeController ec = new EmployeeController();
                        ec.run();
                    } else {
                        view.printMessage("Failed to log in");
                    }
                    break;
                case ("okon"):
                    // place to hide a 'secret' functionality
                    view.printMessage("admin password is lama&L@m@");
                    view.pressEnter();
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
        options.add("Login as administrator");
    }

    private void login() {
        LoginController logCon = new LoginController();
        Customer user = logCon.run();
        if (user != null){
            shopping(user);
        }
    }

    private void register() {
        RegistrationController regCon = new RegistrationController();
        regCon.run();
    }

    private void shopping(Customer activeCustomer) {
        ShoppingController shopCon = new ShoppingController(activeCustomer);
        shopCon.run();
    }
}
