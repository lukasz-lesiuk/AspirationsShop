package com.codecool.access;

import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.customer.Customer;

import java.util.Scanner;

public class LoginController {
    LoginView view;
    Scanner scan;
    CustomerDAO customerDAO;

    public LoginController() {
        this.customerDAO = new CustomerDAOPSQL("database.properties");
        this.view = new LoginView();
        this.scan = new Scanner(System.in);
    }

    public Customer run(){
        Customer user;
        String password;
        String input = "default";
        do {
            view.displayLoginScreen();
            String email = scan.nextLine();
            view.moveToPswd();
            password = Integer.toString(scan.nextLine().hashCode());
            user = customerDAO.searchByMailOnly(email);
            if (!user.getPasswordHash().equals(password)){
                view.printMessage("Wrong username or password. Press Enter and try again or enter q to cancel.");
                input = scan.nextLine();
            }
        } while (isLoginComplete(user, password, input));
        return user;
    }

    private boolean isLoginComplete(Customer user, String password, String input){
        return !user.getPasswordHash().equals(password) || input.equals("q") || input.equals("Q");
    }
}
