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
        List<Customer> peopleList = new ArrayList<Customer>();

        for (int i = 0; i < customersQty; i++) {
            String customerString = retrieveQueryResponseAsString("SELECT * FROM customers LIMIT ? OFFSET ?;",
                                                                    String.valueOf(i), String.valueOf(i-1));
            List<String> attributesList = new ArrayList<>(Arrays.asList(customerString.split(", ")));

            String customerId = attributesList.get(ID_POSITION);
            peopleList.add(getCustomer(customerId));
        }
        return peopleList;
//        return null;
    }

    @Override
    public void update(Customer customerToUpdate) {

    }

    @Override
    public void add(Customer newCustomer) {

    }

    @Override
    public void delete(String id) {

    }


//    private String retrieveQueryResponseAsString(String query, String... insertValues) {
//        ResultSet rs = retrieveQueryResponse(String query, String... insertValues);
//        String queryResponse = "";
//        try {
//            queryResponse = convertResultSetToString(rs);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return queryResponse;
//    }

    private String retrieveQueryResponseAsString(String query, String... insertValues) {

         String queryResponse = "";

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {
            int index = 1;
            for (String insertValue : insertValues)
             pst.setString(index, insertValue);

             try (ResultSet rs = pst.executeQuery()) {
                 System.out.println("---");
                 System.out.println("---");
                 System.out.println("---");
                 queryResponse = convertResultSetToString(rs);
             }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }


    private int getRecordsQty(String tableName) {
        String retrievedString = retrieveQueryResponseAsString("SELECT COUNT(*) FROM ? ", tableName);
        return Integer.parseInt(retrievedString);
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
