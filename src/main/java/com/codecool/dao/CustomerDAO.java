package com.codecool.dao;

import com.codecool.customer.Customer;

import java.util.List;

public interface CustomerDAO {
    public Customer getCustomer(String id);
    public List<Customer> getAllCustomers();
    public void update(Customer customerToUpdate);
    public void add(Customer newCustomer);
    public void delete(String id);
}
