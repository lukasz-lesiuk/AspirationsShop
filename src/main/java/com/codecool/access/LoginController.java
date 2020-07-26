package com.codecool.access;

import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.customer.Customer;

import java.util.List;
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

    private void prepareMenu() {
    }

    public Customer run(){
        Customer user;
        String password;
        do {
            view.displayLoginScreen();
            String email = scan.nextLine();
            view.moveToPswd();
            password = Integer.toString(scan.nextLine().hashCode());
            user = customerDAO.getCustomerByMail(email);
            if (!user.getPasswordHash().equals(password)){
                view.printMessage("Wrong username or password. Press Enter and try again.");
                scan.nextLine();
            }
        } while (!user.getPasswordHash().equals(password));


        return user;
    }
}
