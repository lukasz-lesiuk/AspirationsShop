package com.codecool.access;

import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.customer.Customer;

import java.io.Console;
import java.util.Scanner;

public class LoginController {
    private final LoginView view;
    private final Scanner scan;
    private final CustomerDAO customerDAO;
    private final Console console;

    public LoginController() {
        this.customerDAO = new CustomerDAOPSQL("database.properties");
        this.view = new LoginView();
        this.scan = new Scanner(System.in);
        this.console = System.console();
    }

    public Customer run(){
        Customer user = null;
        String password;
        view.displayLoginScreen();
        String email = scan.nextLine();
        view.moveToPswd();
        password =
                new String(console.readPassword());
        password = Integer.toString(password.hashCode());
        user = customerDAO.searchByMailOnly(email);
        if (user == null || !user.getPasswordHash().equals(password)){
            view.printMessage("Wrong username or password. Press Enter and try again or enter q to cancel.");
            scan.nextLine();
        }

        return user;
    }
}
