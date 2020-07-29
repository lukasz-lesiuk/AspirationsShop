package com.codecool.employeeController;

import com.codecool.access.RegistrationController;
import com.codecool.app.RootView;
import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.view.BasicView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    EmployeeView view = new EmployeeView();
    Scanner scan = new Scanner(System.in);
    List<String> options;
    CustomerDAO dao = new CustomerDAOPSQL("database.properties");

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
//                    dao.
                    break;
                case ("3"):
                    addNewCustomer();
                    stopper();
                    break;
                case ("4"):
                    view.printMessage("Updated customer");
                    break;
                default:
                    view.printMessage("Option not on the list.");
            }
        } while (!choice.equals("0"));
//        scan.close();
    }

    private void prepareMenu() {
        this.options = new ArrayList<>();
        options.add("Show all customers");
        options.add("Search for customer");
        options.add("Add customer");
        options.add("Update customer");
        options.add("Remove customer");
    }

    private void stopper() {
        System.out.println("Press Enter to continue");
        try{System.in.read();}
        catch(Exception e){}
    }

    private void addNewCustomer() {
        RegistrationController regController = new RegistrationController();
        regController.run();
        view.printMessage("Added customer");
    }

    private String getCustomerField(String fieldName) {

        view.printMessage("Please provide " + fieldName);
        String userInput = scan.nextLine();

        return userInput;
    }

}
