package com.codecool.dao;

import com.codecool.customer.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAOPSQL implements CustomerDAO {
    private String url = "jdbc:postgresql://localhost:5432/aspirations_shop";
    private String user = "postgres";
    private String password = "sMuGa1@1";
    private final int ID_POSITION = 0;
    private final int FIRST_NAME_POSITION = 1;
    private final int LAST_NAME_POSITION = 2;
    private final int PHONE_NO_POSITION = 3;
    private final int EMAIL_POSITION = 4;
    private final int CITY_POSITION = 5;
    private final int STREET_POSITION = 6;

    @Override
    public Customer getCustomer(String id) {
        String inputString = retrieveQueryResponseAsString("SELECT * FROM customers WHERE id = ?", id);
        List<String> attributesList = new ArrayList<>(Arrays.asList(inputString.split(", ")));

        try {
            //id retrieval unnecessary since id was declared at the start
            String firstName = attributesList.get(FIRST_NAME_POSITION);
            String lastName = attributesList.get(LAST_NAME_POSITION);
            String phone = attributesList.get(PHONE_NO_POSITION);
            String email = attributesList.get(EMAIL_POSITION);
            String city = attributesList.get(CITY_POSITION);
            String street = attributesList.get(STREET_POSITION);
            return (new Customer(id, firstName, lastName, phone, email, city, street));
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Id did not match any element from DB");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        int customersQty = getRecordsQty("customers");
        List<Customer> customersList = new ArrayList<Customer>();

        for (int i = 1; i < customersQty + 1; i++) {
            String customerString = retrieveQueryResponseAsString("SELECT * FROM customers LIMIT ? OFFSET ?;",
                                                                    i, (i - 1));
            List<String> attributesList = new ArrayList<>(Arrays.asList(customerString.split(", ")));

            String customerId = attributesList.get(ID_POSITION);
            customersList.add(getCustomer(customerId));
        }
        return customersList;
    }

    @Override
    public void update(Customer customerToUpdate) {


    }

    @Override
    public void addCustomer(Customer newCustomer) {

        String query = "INSERT INTO customers(id, first_name, last_name, phone_number, email, city, street) \n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(ID_POSITION + 1, newCustomer.getCustomerId());
            pst.setString(FIRST_NAME_POSITION + 1, newCustomer.getFirstName());
            pst.setString(LAST_NAME_POSITION + 1, newCustomer.getLastName());
            pst.setString(PHONE_NO_POSITION + 1, newCustomer.getPhoneNumber());
            pst.setString(EMAIL_POSITION + 1, newCustomer.getEmailAddress());
            pst.setString(CITY_POSITION + 1, newCustomer.getCity());
            pst.setString(STREET_POSITION + 1, newCustomer.getStreet());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteCustomer(String id) {

        String query = "DELETE FROM customers WHERE id = ?;";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    private String retrieveQueryResponseAsString(String query, String... insertValues) {

         String queryResponse = "";

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {
            int index = 1;
            for (String insertValue : insertValues) {
                pst.setString(index,  insertValue);
                index++;
            }
            try (ResultSet rs = pst.executeQuery()) {
                queryResponse = convertResultSetToString(rs);
             }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }

    //Overload
    private String retrieveQueryResponseAsString(String query, Integer... insertValues) {

        String queryResponse = "";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            int index = 1;
            for (Integer insertValue : insertValues) {
                pst.setInt(index,  insertValue);
                index++;
            }
            try (ResultSet rs = pst.executeQuery()) {
                queryResponse = convertResultSetToString(rs);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }

    private int getRecordsQty(String tableName) {
        int queryResponse = 0;
        String query = "SELECT COUNT(*) FROM customers ";

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) { ;
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                queryResponse = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }

    private String convertResultSetToString(ResultSet resultSet) throws SQLException {
        String queryResponse = "";
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                if (i > 1) {
                    queryResponse = queryResponse + ", " + columnValue;
                } else {
                    queryResponse = columnValue;
                }
            }
        }
        return queryResponse;
    }
}
