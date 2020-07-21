package com.codecool.app;

import com.codecool.IDGenerator;
import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.DAO;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        //temp code
        DAO customerDao = new CustomerDAO();
        Object selectedCustomer = customerDao.getObj("TEMPORARY IRRELEVANT");
    }
}
