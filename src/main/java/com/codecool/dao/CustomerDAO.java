package com.codecool.dao;

import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO implements DAO<Customer> {

    @Override
    public Customer getObj(String id) {
//        String inputString = retrieveQueryToString()
        return null;
    }

    @Override
    public List<Customer> getAllObj() {
        return null;
    }

    @Override
    public void update(Customer objToUpdate) {

    }

    @Override
    public void add(Customer newObj) {

    }

    @Override
    public void delete(String id) {

    }

    private String retrieveQueryToString(String query) {
        String url = "jdbc:postgresql://localhost:5432/aspirations_shop";
        String user = "postgres";
        String password = "sMuGa1@1";

        String queryResponse = "";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
//            view.printResultSet(rs);
            queryResponse = convertResultSetToString(rs);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
//        System.out.println(queryResponse);
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
