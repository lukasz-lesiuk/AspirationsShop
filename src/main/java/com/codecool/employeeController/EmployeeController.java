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
    EmployeeView view = new EmployeeView();
    Scanner scan = new Scanner(System.in);
    List<String> options;
    CustomerDAO dao = new CustomerDAOPSQL("database.properties");
    PasswordGenerator passwordGenerator = new PasswordGenerator();


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
//                    updateCustomer();
                    stopper();
                    break;
                case ("5"):
                    //remove customer
//                    removeCustomer();
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

}
