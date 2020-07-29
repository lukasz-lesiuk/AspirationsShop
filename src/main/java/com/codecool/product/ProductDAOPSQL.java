package com.codecool.product;


import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAOPSQL;
import com.codecool.transaction.SQLTransactionDAO;
import com.codecool.transaction.Transaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAOPSQL implements ProductDAO {
    private final String url;
    private final String username;
    private final String password;

    private final int product_id =0;
    private final int productName = 1;
    private final int description = 3;
    private final int price = 4;
    private final int quanity = 5;
    private final int category = 6;

    public ProductDAOPSQL() {
        String properties_file = "database.properties";
        Properties props = readPropertiesFile("./src/main/resources/" + properties_file);
        this.url = props.getProperty("db.url");
        this.username = props.getProperty("db.user");
        this.password = props.getProperty("db.passwd");
    }

    @Override
    public Product getProduct(String Name) {
        String query = "SELECT * FROM products WHERE productName = ?";
        Product product = null;
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(query)) {
                 pst.setString(productName, Name);
                ResultSet rs = pst.executeQuery();
                System.out.println(rs.toString());
                String productId = null;
                String productName = null;
                String description = null;
                String price = null;
                String quantity = null;
                String category = null;

                while (rs.next()){
                    System.out.println("rs.toString()");
                    productId = rs.getString("product_id");
                    productName = rs.getString("productName");
                    description = rs.getString("description");
                    price = rs.getString("price");
                    quantity = rs.getString("quantity");
                    category = rs.getString("category");
                }

                product = new Product(productId,productName,description,price,quantity,category);
        }
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
            return  product;

    }

    @Override
    public Product getProductByPhrase(String phrase) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void updateProduct(Product productToUpdate) {

    }

    @Override
    public void addProduct(Product newProduct) {

    }

    @Override
    public void deleteProduct(String product_Id) {

    }

    private Properties readPropertiesFile(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
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

    private String retrieveQueryResponseAsString(String query, String... insertValues) {

        String queryResponse = "";

        try (Connection con = DriverManager.getConnection(url, username, password);
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
