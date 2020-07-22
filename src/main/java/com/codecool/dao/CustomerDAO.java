package com.codecool.dao;

import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO implements DAO<Customer> {

    @Override
    public Customer getObj(String id) {
        String inputString = retriveByIDToString(id);
        List<String> attributesList = new ArrayList<>(Arrays.asList(inputString.split(", ")));

        try {
            //id retrieval unnecessary since id was declared at the start
            String firstName = attributesList.get(1);
            String lastName = attributesList.get(2);
            String phone = attributesList.get(3);
            String email = attributesList.get(4);
            String city = attributesList.get(5);
            String street = attributesList.get(6);
            return (new Customer(id, firstName, lastName, phone, email, city, street));
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("Id did not match any element from DB");
            System.exit(1);
            return null;
        }
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

    private String retriveByIDToString(String id) {
        String url = "jdbc:postgresql://localhost:5432/aspirations_shop";
        String user = "postgres";
        String password = "sMuGa1@1";

        String query = "SELECT * FROM customers WHERE id = ?";

        String queryResponse = "";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
//             pst.setString(1, id);
             pst.setString(1, id);
             try (ResultSet rs = pst.executeQuery()) {
                 queryResponse = convertResultSetToString(rs);
             }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }

//    public String getTemplateText(String name) {
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement("SELECT templateText FROM TEMPLATE WHERE " + "templateTag = ?")) {
//            stmt.setString(1, name);
//            try (ResultSet rs = stmt.executeQuery()) {
//                System.out.println("Set Text...");
//                String tempText = rs.getString("templateText");
//                return tempText;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }




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
