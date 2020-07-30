package com.codecool.employeeController;

import com.codecool.access.PasswordGenerator;
import com.codecool.access.RegistrationController;
import com.codecool.app.RootView;
import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.view.BasicView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
//    public enum OptionsEnum {firstName, lastName, phone, email, city, address};
    EmployeeView view = new EmployeeView();
    Scanner scan = new Scanner(System.in);
    List<String> options;
    CustomerDAO dao = new CustomerDAOPSQL("database.properties");
    PasswordGenerator passwordGenerator = new PasswordGenerator();
    int FIRST_NAME = 1;
    int LAST_NAME = 2;
    int PHONE = 3;
    int EMAIL = 4;
    int CITY = 5;
    int ADDRESS = 6;


    public void run() {
        prepareMenu();
        String choice;
        do {
            view.displayMainMenu(options);
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    List<Customer> customers = dao.getAllCustomers();
                    view.printALlCustomers(customers);
                    stopper();
                    break;
                case ("2"):
                    //TODO search for customer
                    break;
                case ("3"):
                    resetPassword();
                    stopper();
                    break;
                case ("4"):
                    updateCustomer();
                    stopper();
                    break;
                case ("5"):
                    //remove customer
                    removeCustomer();
                    stopper();
                    break;
                default:
                    view.printMessage("Option not on the list.");
            }
        } while (!choice.equals("0"));
    }

    private void prepareMenu() {
        this.options = new ArrayList<>();
        options.add("Show all customers");
        options.add("Search for customer");
        options.add("Reset customer password");
        options.add("Update customer");
        options.add("Remove customer");
    }

    private void stopper() {
        System.out.println("Press Enter to continue");
        try{System.in.read();}
        catch(Exception e){}
    }

    private String resetPassword() {
        Customer targetCustomer = getValidCustomer();
        String newPassword = passwordGenerator.getPassword();
        targetCustomer.setPasswordHash(newPassword);

        dao.updateCustomer(targetCustomer);
        view.printMessage("Password updated");
        return newPassword;
    }

    private Customer getValidCustomer () {
        Customer pickedCustomer = null;

        while (pickedCustomer == null) {
            view.printMessage("Provide valid id of customer: ");
            String userInput = scan.nextLine();
            pickedCustomer = dao.getCustomer(userInput);
        }
        return pickedCustomer;
    }

    private void removeCustomer() {
        Customer targetCustomer = getValidCustomer();
        dao.deleteCustomer(targetCustomer.getCustomerId());
        view.printMessage("Removed customer");
    }

    private void updateCustomer() {
        Customer targetCustomer = getValidCustomer();

        List<String> optionList = new ArrayList<String>();
        optionList.add("First name");
        optionList.add("Last name");
        optionList.add("Phone number");
        optionList.add("Email address");
        optionList.add("City");
        optionList.add("Street and apartment no");

        String choice;
        boolean shouldRun = true;

        while (shouldRun) {
            view.printOptions(optionList, "Select property you want to change");
            view.printMessage("(0) Cancel");
//           view.displayMainMenu(options);
            choice = scan.nextLine();
            String newValue;

            switch (choice) {
                case ("1"):
                    newValue = input("provide new First Name");
                    System.out.println("NEW VALUE " + newValue);
                    targetCustomer.setFirstName(newValue);
                    break;
                case ("2"):
                    newValue = input("provide new Last Name");
                    targetCustomer.setLastName(newValue);
                    break;
                case ("3"):
                    newValue = input("provide new phone number");
                    targetCustomer.setPhoneNumber(newValue);
                    break;
                case ("4"):
                    newValue = input("provide new email adress");
                    targetCustomer.setEmailAddress(newValue);
                    break;
                case ("5"):
                    newValue = input("provide new city");
                    targetCustomer.setCity(newValue);
                    break;
                case ("6"):
                    newValue = input("provide address");
                    targetCustomer.setStreet(newValue);
                    break;
                case ("0"):
                    shouldRun = false;
                    break;
                default:
                    view.printMessage("Option not on the list.");
            }
            dao.updateCustomer(targetCustomer);
        }
    }

    private String input(String message){
        System.out.println(message + ": ");
        String inputValue = scan.nextLine();
        view.clear();
        return inputValue;
    }
}
