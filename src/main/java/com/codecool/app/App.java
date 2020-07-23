package com.codecool.app;

import com.codecool.IDGenerator;
import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        // temp code for testing, feel fre to delete - Lukasz Lesiuk-------------------------------
        CustomerDAO customerDao = new CustomerDAOPSQL("database.properties");
        IDGenerator generator = new IDGenerator();
        Customer selectedCustomer = customerDao.getCustomer("JgsMz0d1");
        System.out.println(selectedCustomer.getLastName());
        List<Customer> customerList = customerDao.getAllCustomers();

//        Customer newCustomer = new Customer(id, "Adam", "Smith", "+121121121212",
//                                            "adam@smith", "Nowhere", "404");
//        customerDao.addCustomer(newCustomer);
//        customerDao.deleteCustomer("H[SK$jT1");
//        customerDao.updateCustomer(new Customer("/i1s)]+h", "Adam", "Smith", "+121121121212",
//                "adam@smith", "Nowhere", "505"));
        System.out.println("END");
        //-----------------------------------------------------------------------------------------
    }
}
