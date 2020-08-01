package com.codecool.dao;

import com.codecool.customer.Customer;
import com.codecool.view.BasicView;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAOPSQL implements CustomerDAO {
    private String url;
    private String user;
    private String password;

    private Column column;
    private ColumnPublic columnPublic;

    BasicView view;

    public CustomerDAOPSQL(String properties_file) {
        this.view = new BasicView();
        Properties props = readPropertiesFile("./src/main/resources/" + properties_file);
        this.url = props.getProperty("db.url");
        this.user = props.getProperty("db.user");
        this.password = props.getProperty("db.passwd");
    }

    @Override
    public Customer getCustomer(String id) {
        String inputString = retrieveQueryResponseAsString("SELECT * FROM customers WHERE id = ?", id).get(0);
        return makeCustomerFromString(inputString);
    }

    @Override
    public Customer getCustomerByMail(String email) {
        // TODO implement function as per name

        return getCustomer("?p7<ks+n"); // dumb placeholder implementation
    }

    @Override
    public List<Customer> getAllCustomers() {
        int customersQty = getRecordsQty("customers");
        List<Customer> customersList = new ArrayList<Customer>();

        for (int i = 1; i < customersQty + 1; i++) {
            String customerString = retrieveQueryResponseAsString("SELECT * FROM customers LIMIT ? OFFSET ?;",
                                                                    1, (i - 1)).get(0);
            List<String> attributesList = new ArrayList<>(Arrays.asList(customerString.split(", ")));

            String customerId = attributesList.get(column.ID.position);
            customersList.add(getCustomer(customerId));
        }
        return customersList;
    }

    @Override
    public void updateCustomer(Customer customerToUpdate) {
        String query = "UPDATE customers SET  first_name = ?, last_name = ?," +
                " phone_number = ? , email = ? , city = ?, street = ?, hash_password = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            //TODO magic numbers
            pst.setString(1, customerToUpdate.getFirstName());
            pst.setString(2, customerToUpdate.getLastName());
            pst.setString(3, customerToUpdate.getPhoneNumber());
            pst.setString(4, customerToUpdate.getEmailAddress());
            pst.setString(5, customerToUpdate.getCity());
            pst.setString(6, customerToUpdate.getStreet());
            pst.setString(7, customerToUpdate.getPasswordHash());
            pst.setString(8, customerToUpdate.getCustomerId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    @Override
    public void addCustomer(Customer newCustomer) {

        String query = "INSERT INTO customers(id, first_name, last_name, phone_number, email, city, street, hash_password) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(column.ID.position + 1, newCustomer.getCustomerId());
            pst.setString(column.FIRST_NAME.position + 1, newCustomer.getFirstName());
            pst.setString(column.LAST_NAME.position + 1, newCustomer.getLastName());
            pst.setString(column.PHONE_NO.position+ 1, newCustomer.getPhoneNumber());
            pst.setString(column.EMAIL.position+ 1, newCustomer.getEmailAddress());
            pst.setString(column.CITY.position + 1, newCustomer.getCity());
            pst.setString(column.STREET.position+ 1, newCustomer.getStreet());
            pst.setString(column.HASH.position+ 1, newCustomer.getPasswordHash());

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

    @Override
    public List<Customer> searchForCustomers(String inquiry) {
        List<Customer> outputList = new ArrayList<Customer>();
        String queryForPreparedStatement = (generateQueryForSearch());
        List<String> inputList = retrieveQueryResponseAsString(queryForPreparedStatement, inquiry, inquiry,
                inquiry, inquiry, inquiry, inquiry, inquiry);

        for (String nextString : inputList) {
            Customer nextCustomer = makeCustomerFromString(nextString);
            outputList.add(nextCustomer);
        }

        return outputList;
    }

    private Customer makeCustomerFromString(String inputString) {
        List<String> attributesList = new ArrayList<>(Arrays.asList(inputString.split(", ")));
        //TODO may return null - fix it
        Customer outputCustomer = null;
        try {
            String id = attributesList.get(column.ID.position);
            String firstName = attributesList.get(column.FIRST_NAME.position);
            String lastName = attributesList.get(column.LAST_NAME.position);
            String phone = attributesList.get(column.PHONE_NO.position);
            String email = attributesList.get(column.EMAIL.position);
            String city = attributesList.get(column.CITY.position);
            String street = attributesList.get(column.STREET.position);
            String hash = attributesList.get(column.HASH.position);
            outputCustomer =  new Customer(id, firstName, lastName, phone, email, city, street, hash);
        } catch (java.lang.IndexOutOfBoundsException e) {
//            throw new IllegalArgumentException("Id did not match any element from DB");
            view.printError("Incorrect ID");
        }
        return outputCustomer;
    }

    private String generateQueryForSearch() {
        StringBuilder strBuilder = new StringBuilder("SELECT * FROM customers WHERE ");

        int index = 0;
        for (ColumnPublic columnName : columnPublic.values()) {

            strBuilder.append(columnName);
            strBuilder.append(" LIKE ");
            strBuilder.append("?");
            index++;
            if (index < columnPublic.values().length) {
                strBuilder.append(" OR ");
            }
        }
        return strBuilder.toString();
    }

    private List<String> retrieveQueryResponseAsString(String query, String... insertValues) {

         List<String> queryResponse = new ArrayList<String>();

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {
            int index = 1;
            for (String insertValue : insertValues) {
                pst.setString(index,  insertValue);
                index++;
            }
            try (ResultSet rs = pst.executeQuery()) {
                queryResponse = convertResultSetToList(rs);
             }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }

    //Overload
    private List<String> retrieveQueryResponseAsString(String query, Integer... insertValues) {

        List<String> queryResponse = new ArrayList<String>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            int index = 1;
            for (Integer insertValue : insertValues) {
                pst.setInt(index,  insertValue);
                index++;
            }
            try (ResultSet rs = pst.executeQuery()) {
                queryResponse = convertResultSetToList(rs);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  queryResponse;
    }

    private int getRecordsQty(String tableName) {
        int queryResponse = 0;
        String query = "SELECT COUNT(*) FROM " + tableName;

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)) {
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

    private List<String> convertResultSetToList(ResultSet resultSet) throws SQLException {
        List<String> queryResponse = new ArrayList<String>();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            String nextRow = "";
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                if (i > 1) {
                    nextRow = nextRow + ", " + columnValue;
                } else {
                    nextRow = columnValue;
                }
            }
            queryResponse.add(nextRow);
        }
        return queryResponse;
    }


    private Properties readPropertiesFile(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
