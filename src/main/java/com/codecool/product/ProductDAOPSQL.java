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

    private final int product_id = 0;
    private final int productName = 1;
    private final int description = 2;
    private final int price = 3;
    private final int quanity = 4;
    private final int category = 5;

    public ProductDAOPSQL() {
        String properties_file = "database.properties";
        Properties props = readPropertiesFile("./src/main/resources/" + properties_file);
        this.url = props.getProperty("db.url");
        this.username = props.getProperty("db.user");
        this.password = props.getProperty("db.passwd");
    }

    @Override
    public Product getProduct(String Name) {
        String inputString = retrieveQueryResponseAsString("SELECT * FROM products WHERE productName = ?", Name);
        List<String> attributesList = new ArrayList<>(Arrays.asList(inputString.split(", ")));
        try {
            //id retrieval unnecessary since id was declared at the start
            String product_Id = attributesList.get(product_id);
            String product_Name = attributesList.get(productName);
            String product_Description = attributesList.get(description);
            String product_Price = attributesList.get(price);
            String product_Quanity = attributesList.get(quanity);
            String product_Category = attributesList.get(category);

            return (new Product(product_Id,product_Name,product_Description,product_Price,product_Quanity,product_Category));
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Id did not match any element from DB");
        }

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
