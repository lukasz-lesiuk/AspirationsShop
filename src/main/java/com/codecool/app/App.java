package com.codecool.app;

import com.codecool.IDGenerator;
import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        //temp code
        CustomerDAO customerDao = new CustomerDAOPSQL();
        IDGenerator generator = new IDGenerator();
//        Customer selectedCustomer = customerDao.getCustomer("JgsMz0d1");
//        List<Customer> customerList = customerDao.getAllCustomers();
        String id = generator.generateID();
        Customer newCustomer = new Customer(id, "Adam", "Smith", "+121121121212",
                                            "adam@smith", "Nowhere", "404");
//        customerDao.addCustomer(newCustomer);
//        customerDao.deleteCustomer("H[SK$jT1");
        System.out.println("END");

    }
}
