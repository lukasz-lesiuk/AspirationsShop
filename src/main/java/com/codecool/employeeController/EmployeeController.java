package com.codecool.employeeController;

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
                    // You can add any function you wish to test here
//                    Customer newCustomer = new Customer("1234", "Name", "Lastname",
//                                        "+404043655", "emali@email.com", "city",
//                                        "street", "passhash");
//                    CustomerDAO daoInstance = new CustomerDAOPSQL();
//                    daoInstance.addCustomer(newCustomer);
//                    daoInstance.getCustomer("1234");
//                    System.out.println("AddedCustomer");
                    break;
                case ("okon"):
                    // place to hide a 'secret' functionality
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
    }

    private void stopper() {
        System.out.println("Press Enter to continue");
        try{System.in.read();}
        catch(Exception e){}
    }

}
