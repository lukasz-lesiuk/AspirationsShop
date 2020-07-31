package com.codecool.dao;

import com.codecool.customer.Customer;

import java.time.temporal.ChronoUnit;
import java.util.List;

public interface CustomerDAO {
    public Customer getCustomer(String id);
    public List<Customer> getAllCustomers();
    public void updateCustomer(Customer customerToUpdate);
    public void addCustomer(Customer newCustomer);
    public void deleteCustomer(String id);
    public List<Customer> searchForCustomers(String inquiry);
}
