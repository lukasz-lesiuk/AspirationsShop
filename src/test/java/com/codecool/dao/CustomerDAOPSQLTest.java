package com.codecool.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codecool.customer.Customer;
import junit.framework.TestCase;

public class CustomerDAOPSQLTest extends TestCase {

    public void testGetExistingCustomer() {
        CustomerDAO customerDAO= new CustomerDAOPSQL();
        Customer selectedCustomer = customerDAO.getCustomer("x%[>j!X#");
        Customer refCustomer = new Customer("x%[>j!X#", "Adam", "Nowak", "+48695609770", "alpaka@mlamamail.com", "Cracow", "Rakowicka 21");
        assertEquals(selectedCustomer.getCustomerId(), refCustomer.getCustomerId());
        assertEquals(selectedCustomer.getFirstName(), refCustomer.getFirstName());
        assertEquals(selectedCustomer.getLastName(), refCustomer.getLastName());
        assertEquals(selectedCustomer.getPhoneNumber(), refCustomer.getPhoneNumber());
        assertEquals(selectedCustomer.getEmailAddress(), refCustomer.getEmailAddress());
        assertEquals(selectedCustomer.getCity(), refCustomer.getCity());
        assertEquals(selectedCustomer.getStreet(), refCustomer.getStreet());
    }

    public void testGetNonexistentCustomer() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    CustomerDAO customerDAO = new CustomerDAOPSQL();
                    Customer selectedCustomer = customerDAO.getCustomer("invalidId");
                }
        );
        assertEquals("Id did not match any element from DB", exception.getMessage());
    }

    public void testGetAllCustomer() {
    }

    public void testUpdate() {
    }

    public void testAdd() {
    }

    public void testDelete() {
    }
}