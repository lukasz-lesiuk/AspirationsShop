package com.codecool.access;

import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.customer.Customer;
import com.codecool.IDGenerator;

import java.util.Scanner;

public class RegistrationController {
    RegistrationView view;
    Scanner scan;
    CustomerDAO customerDAO;
    IDGenerator idGen;
    PasswordGenerator passwordGenerator;

    public RegistrationController() {
        this.customerDAO = new CustomerDAOPSQL("database.properties");
        this.view = new RegistrationView();
        this.idGen = new IDGenerator();
        this.scan = new Scanner(System.in);
        this.passwordGenerator = new PasswordGenerator();
    }

    public void run(){
        view.clear();
        Customer newUser = createCustomer();
        customerDAO.addCustomer(newUser);
    }

    private Customer createCustomer() {
        String id = idGen.generateID();
        String firstName = input("Enter your first name");
        String lastName = input("Enter your last name");
        String phoneNumber = input("Enter your phone number");
        String emailAddress = input("Enter your email address");
        System.out.println("Address:");
        String city = input("City");
        String street = input("Street and apartment number");
        System.out.println();
        String password = getPassword();
        return new Customer(id, firstName, lastName, phoneNumber, emailAddress, city, street, password);
    }

    private String input(String message){
        System.out.print(message + ": ");
        String input = scan.nextLine();
        view.clear();
        return input;
    }

    private String getPassword() {
        return passwordGenerator.getPassword();
//        String password;
//        String passwordConfirm;
//        PasswordChecker checker = new PasswordChecker();
//        do {
//            password = input("Enter password");
//            while (!checker.isPasswordGood(password)) {
//                view.printMessage("This password didn't meet the policy requirements.");
//                input("Enter password");
//            }
//            passwordConfirm = input("Enter password again");
//            if (!passwordConfirm.equals(password)){
//                view.printMessage("Passwords didn't match. Press Enter and try again.");
//                scan.nextLine();
//            }
//        } while (!passwordConfirm.equals(password));
//
//        return Integer.toString(password.hashCode());
    }
}
