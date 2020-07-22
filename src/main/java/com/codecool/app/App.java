package com.codecool.app;

import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;

public class App {
    public static void main( String[] args ) {
        //temp code
        CustomerDAO customerDao = new CustomerDAOPSQL();
        Customer selectedCustomer = customerDao.getCustomer("JgsMz0d1");
        System.out.println("ama");
    }
}
