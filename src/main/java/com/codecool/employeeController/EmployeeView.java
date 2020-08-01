package com.codecool.employeeController;

import com.codecool.customer.Customer;
import com.codecool.view.basicView;

import java.util.List;

public class EmployeeView extends basicView {
    void displayMainMenu(List<String> options){
        clear();
        printOptions(options, "You are logged in as employee");
        printMessage("(0) Logout");
    }

    void printCustomersList(List<Customer> customerList) {
        for(Customer customer : customerList) {
            printCustomer(customer);
        }
    }
}
