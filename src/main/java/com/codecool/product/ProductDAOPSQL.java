package com.codecool.product;


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
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;


    public ProductDAOPSQL() {
        String properties_file = "database.properties";
        Properties props = readPropertiesFile("./src/main/resources/" + properties_file);
        this.URL = props.getProperty("db.url");
        this.USERNAME = props.getProperty("db.user");
        this.PASSWORD = props.getProperty("db.passwd");
    }

    @Override
    public Product getProduct(String Name) {
        String query = "SELECT * FROM products WHERE lower(productName) = ?";
        Name = Name.toLowerCase();
        Product product = null;
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
                 pst.setString(1, Name);
                ResultSet rs = pst.executeQuery();
                String productId = null;
                String productName = null;
                String description = null;
                String price = null;
                String quantity = null;
                String category = null;

                while (rs.next()){
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
    public List<Product> getProductByPhrase(String phrase) {
        String query = "SELECT * FROM products WHERE lower(productName) LIKE ? OR lower(description) LIKE ? OR lower(category) LIKE ? ";
        phrase = phrase.toLowerCase();
        phrase = "%" + phrase + "%";

        List<Product> productsList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);

             PreparedStatement pst = con.prepareStatement(query)){
                 pst.setString(1,phrase);
                 pst.setString(2,phrase);
                 pst.setString(3,phrase);


                 ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("productName");
                Product product = getProduct(productName);
                productsList.add(product);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return productsList;


    }

    @Override
    public List<Product> getAllProducts() {
        String query = "SELECT * FROM products";

        List<Product> productsList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String productName = rs.getString("productName");
                Product product = getProduct(productName);
                productsList.add(product);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return productsList;
    }

    @Override
    public List<Product> getProductByCategory(String categoryName) {
        String query = "SELECT * FROM products WHERE lower(category) = ? ";
        categoryName = categoryName.toLowerCase();


        List<Product> productsList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);

             PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1,categoryName);


            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("productName");
                Product product = getProduct(productName);
                productsList.add(product);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return productsList;
    }

    @Override
    public void updateProductQuantity(Product productToUpdate, int newQuantity) {
        productToUpdate.setQuantity(newQuantity);
        String query = "UPDATE products SET quantity = ? WHERE product_id = ? ";


        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,newQuantity);
            String temp = productToUpdate.getProductID();
            pst.setInt(2,Integer.parseInt(temp));

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }


    }



    @Override
    public void addProduct(Product newProduct) {
        String query = "INSERT INTO products (productName,description,price,quantity,category)  VALUES(?,?,?,?,?);";


        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, newProduct.getProductName());
            pst.setString(2,newProduct.getDescription());
            pst.setInt(3,newProduct.getPrice());
            pst.setInt(4,newProduct.getQuantity());
            pst.setString(5,newProduct.getCategory());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProduct(String product_Id) {
        String query = "DELETE FROM products WHERE product_id = ?;";


        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, Integer.parseInt(product_Id));

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(SQLTransactionDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }


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

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
