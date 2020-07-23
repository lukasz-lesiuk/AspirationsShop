package com.codecool.dao;

import com.codecool.customer.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final int ID_POSITION = 0;
    private final int FIRST_NAME_POSITION = 1;
    private final int LAST_NAME_POSITION = 2;
    private final int PHONE_NO_POSITION = 3;
    private final int EMAIL_POSITION = 4;
    private final int CITY_POSITION = 5;
    private final int STREET_POSITION = 6;

    public CustomerDAOPSQL() {
//        //Creating a File object for directory
//        File directoryPath = new File("resources");
//        //List of all files and directories
//        String contents[] = directoryPath.list();
//        System.out.println("List of files and directories in the specified directory:");
//        for(int i=0; i<contents.length; i++) {
//            System.out.println(contents[i]);
//        }

        Properties props = readPropertiesFile("/home/lukasz-lesiuk/IdeaProjects/AspirationsShop/src/main/resources/database.properties");
        this.url = props.getProperty("db.url");
        this.user = props.getProperty("db.user");
        this.password = props.getProperty("db.passwd");
;
//        this.url = "jdbc:postgresql://localhost:5432/aspirations_shop";
//        this.user = "postgres";
//        this.password = "sMuGa1@1";
    }

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
    public void updateCustomer(Customer customerToUpdate) {
        String query = "UPDATE customers SET  first_name = ?, last_name = ?," +
                " phone_number = ? , email = ? , city = ?, street = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, customerToUpdate.getFirstName());
            pst.setString(2, customerToUpdate.getLastName());
            pst.setString(3, customerToUpdate.getPhoneNumber());
            pst.setString(4, customerToUpdate.getEmailAddress());
            pst.setString(5, customerToUpdate.getCity());
            pst.setString(6, customerToUpdate.getStreet());
            pst.setString(7, customerToUpdate.getCustomerId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAOPSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    @Override
    public void addCustomer(Customer newCustomer) {

        String query = "INSERT INTO customers(id, first_name, last_name, phone_number, email, city, street) " +
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

    private Properties readProperties() {

        Properties props = new Properties();
        Path myPath = Paths.get("database2.properties");
///home/lukasz-lesiuk/IdeaProjects/AspirationsShop/src/main/resources/

        try {
            BufferedReader bf = Files.newBufferedReader(myPath,
                    StandardCharsets.UTF_8);
            props.load(bf);
        } catch (IOException ex) {
            Logger.getLogger(CustomerDAOPSQL.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return props;
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
