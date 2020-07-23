package com.codecool.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codecool.customer.Customer;
import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOPSQLTest extends TestCase {

    public void testGetExistingCustomer() {
        CustomerDAO customerDAO= new CustomerDAOPSQL("database_test.properties");
        Customer selectedCustomer = customerDAO.getCustomer("x%[>j!X#");
        Customer refCustomer = new Customer("x%[>j!X#", "Adam", "Nowak", "+48695609770", "alpaka@mlamamail.com", "Cracow", "Rakowicka 21", "12745634");
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
                    CustomerDAO customerDAO = new CustomerDAOPSQL("database_test.properties");
                    Customer selectedCustomer = customerDAO.getCustomer("invalidId");
                }
        );
        assertEquals("Id did not match any element from DB", exception.getMessage());
    }

    public void testGetAllCustomerExistingDB() {
        CustomerDAO customerDAO= new CustomerDAOPSQL("database_test.properties");
        List<Customer> actualCustomers = customerDAO.getAllCustomers();
        List<Customer> expectedCustomers = new ArrayList<Customer>();
        expectedCustomers.add(new Customer("x%[>j!X#", "Adam", "Nowak", "+48695609770",
                                        "alpaka@mlamamail.com", "Cracow", "Rakowicka 21", "12745634"));
        expectedCustomers.add(new Customer("JgsMz0d1", "Szymon", "Kowalsk", "+48694869284",
                                        "mail2@mail.com", "Cracow", "ulica 12-121", "32155726"));
        expectedCustomers.add(new Customer("ve55[R<W", "John", "Lama", "+48690437501",
                                        "mail3@diffrentmail.com", "Dehli", "inna ulica 7", "243663543"));
        expectedCustomers.add(new Customer("M+MIQP<f", "Mike", "Huntingthon", "+48690800209",
                                        "mail4@mail.com", "Nagoja", "yetAnotherStreet 5", "2354624"));
        Field[] fieldsActual0 = actualCustomers.get(0).getClass().getDeclaredFields();
        Field[] fieldsExpected0 = expectedCustomers.get(0).getClass().getDeclaredFields();

        Field[] fieldsActual1 = actualCustomers.get(0).getClass().getDeclaredFields();
        Field[] fieldsExpected1 = expectedCustomers.get(0).getClass().getDeclaredFields();

        Field[] fieldsActual2 = actualCustomers.get(0).getClass().getDeclaredFields();
        Field[] fieldsExpected2 = expectedCustomers.get(0).getClass().getDeclaredFields();

        Field[] fieldsActual3 = actualCustomers.get(0).getClass().getDeclaredFields();
        Field[] fieldsExpected3 = expectedCustomers.get(0).getClass().getDeclaredFields();

        assertThat(fieldsActual0, is(fieldsExpected0));
        assertThat(fieldsActual1, is(fieldsExpected1));
        assertThat(fieldsActual2, is(fieldsExpected2));
        assertThat(fieldsActual3, is(fieldsExpected3));

    }

    public void testUpdateLastName() {
        CustomerDAO customerDAO= new CustomerDAOPSQL("database_test.properties");
        String updatedLastName = "NewLastName";
        customerDAO.updateCustomer(new Customer("x%[>j!X#", "Adam", updatedLastName,
                "+48695609770", "alpaka@mlamamail.com", "Cracow", "Rakowicka 21", "12745634"));
        Customer selectedCustomer = customerDAO.getCustomer("x%[>j!X#");
        assertEquals(updatedLastName, selectedCustomer.getLastName());
        customerDAO.updateCustomer(new Customer("x%[>j!X#", "Adam", "Nowak",
                "+48695609770", "alpaka@mlamamail.com", "Cracow", "Rakowicka 21", "12745634"));
    }

    public void testAdd() {
    }

    public void testDelete() {
    }
}