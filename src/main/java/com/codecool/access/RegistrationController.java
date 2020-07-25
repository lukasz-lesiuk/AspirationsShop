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

    public RegistrationController() {
        this.customerDAO = new CustomerDAOPSQL("/home/irrehaare/CodeCool/Java/AspirationsShop/src/main/resources/");
        this.view = new RegistrationView();
        this.idGen = new IDGenerator();
        this.scan = new Scanner(System.in);
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
        String city = input("City: ");
        String street = input("Street and apartment number:");
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
        String password;
        do {
            password = input("Enter password");
            while (!isPasswordGood(password)) {
                view.printMessage("This password didn't meet the policy requirements.");
                input("Enter password");
            }
            if (!input("Enter password again").equals(password)){
                view.printMessage("Passwords didn't match. Press Enter and try again.");
                scan.nextLine();
            }
        } while (!input("Enter password again").equals(password));

        return Integer.toString(password.hashCode());
    }

    private boolean isPasswordGood(String password) {
        boolean result = true;
        if (password.length()<8 || !hasDifferentChars(password)){
            result = false;
        }
        return result;
    }

    private boolean hasDifferentChars(String password) {
        int hasDigit = 0;
        int hasUpper = 0;
        int hasLower = 0;
        int hasSpecial = 0;
        char[] chars = password.toCharArray();

        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                hasDigit = 1;
            } else if (Character.isLowerCase(aChar)){
                hasLower = 1;
            } else if (Character.isUpperCase(aChar)){
                hasUpper = 1;
            } else if (!Character.isLetterOrDigit(aChar)){
                hasSpecial = 1;
            }
        }

        int sum = hasDigit + hasLower + hasUpper + hasSpecial;
        return sum >= 3;
    }
}
