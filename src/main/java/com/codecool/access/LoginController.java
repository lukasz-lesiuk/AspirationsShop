package com.codecool.access;

import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.customer.Customer;
import com.codecool.view.BasicView;

import java.util.Scanner;

public class LoginController {
    private final LoginView view;
    private final Scanner scan;
    private final CustomerDAO customerDAO;

    public LoginController() {
        this.customerDAO = new CustomerDAOPSQL("database.properties");
        this.view = new LoginView();
        this.scan = new Scanner(System.in);
    }

    public Customer run(){
        Customer user = null;
        String password;
        String input = "default";
        try {
            view.displayLoginScreen();
            String email = scan.nextLine();
            view.moveToPswd();
            password = Integer.toString(scan.nextLine().hashCode());
            user = customerDAO.searchByMailOnly(email);
            if (!user.getPasswordHash().equals(password)){
                view.printMessage("Wrong username or password. Press Enter and try again or enter q to cancel.");
                input = scan.nextLine();
            }
            } catch (NullPointerException e) {
                view.printError("Failed to log in");
                BasicView view = new BasicView();
                view.pressEnter();
            }

        return user;
    }

    private boolean isLoginComplete(Customer user, String password, String input){
        try{
            return !user.getPasswordHash().equals(password) || input.equals("q") || input.equals("Q");
        } catch (NullPointerException e) {
            return true;
        }
    }
}
