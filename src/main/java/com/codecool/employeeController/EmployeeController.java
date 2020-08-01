package com.codecool.employeeController;

import com.codecool.access.PasswordGenerator;
import com.codecool.access.RegistrationController;
import com.codecool.app.RootView;
import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.transaction.SQLTransactionDAO;
import com.codecool.transaction.Transaction;
import com.codecool.transaction.TransactionDAO;
import com.codecool.transaction.TransactionView;
import com.codecool.view.BasicView;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    EmployeeView view = new EmployeeView();
    Scanner scan = new Scanner(System.in);
    List<String> options;
    CustomerDAO dao = new CustomerDAOPSQL("database.properties");
//    TransactionDAO transactionDAO
    PasswordGenerator passwordGenerator = new PasswordGenerator();
    TransactionView transView = new TransactionView();


    public void run() {
        prepareMenu();
        String choice;
        List<Customer> customers;
        do {
            view.displayMainMenu(options);
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    customers = dao.getAllCustomers();
                    view.printCustomersList(customers);
                    stopper();
                    break;
                case ("2"):
                    view.printMessage("What are you searching for:");
                    String inquiry = scan.nextLine();
                    customers = dao.searchForCustomers(inquiry);
                    view.printCustomersList(customers);
                    stopper();
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
                case ("6"):
                    Date startDate = Date.valueOf("1900-12-01");
                    Date endDate = Date.valueOf("2100-12-01");
                    getTransactionByDate(startDate, endDate);
                    view.pressEnter();
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
        options.add("Remove customer");;
        options.add("Show all transactions");
    }

    private void stopper() {
        System.out.println("Press Enter to continue");
        try{System.in.read();}
        catch(Exception e){}
    }

    private void resetPassword() {
        String newPassword = null;
        Customer targetCustomer = getValidCustomer();

        if (targetCustomer != null) {
            newPassword = passwordGenerator.getPassword();
            targetCustomer.setPasswordHash(newPassword);

            dao.updateCustomer(targetCustomer);
            view.printMessage("Password updated");
        } else {

            view.printMessage("Id do not match any record from database");
        }
    }

    private Customer getValidCustomer () {
        Customer pickedCustomer = null;
        String userInput = "";

        while (pickedCustomer == null && (!userInput.equals("q"))) {
            view.printMessage("Provide valid id of customer or press q to cancel: ");
            userInput = scan.nextLine();
            pickedCustomer = dao.getCustomer(userInput);
        }
        return pickedCustomer;
    }

    private void removeCustomer() {
        Customer targetCustomer = getValidCustomer();
        try {
            dao.deleteCustomer(targetCustomer.getCustomerId());
            view.printMessage("Removed customer");
        } catch (NullPointerException e) {
            view.printMessage("Removal cancelled");
        }

    }

    private void updateCustomer() {
        Customer targetCustomer = getValidCustomer();

        if (targetCustomer != null) {
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
        } else {
            view.printMessage("update cancelled");
        }

    }

    private String input(String message){
        System.out.println(message + ": ");
        String inputValue = scan.nextLine();
        view.clear();
        return inputValue;
    }

    private void getTransactionByDate(Date startDate, Date endDate){
        TransactionDAO SQLTransDAO = new SQLTransactionDAO();

//        java.sql.Date from = Date.valueOf("2020-12-01");
//        java.sql.Date to = Date.valueOf("2020-12-01");

        java.sql.Date from = startDate;
        java.sql.Date to = endDate;
        List<Transaction> transactionList =
                SQLTransDAO.getAllTransactionsByDate(from, to);
        transView.printTransactions(transactionList);
    }
}
