package com.codecool.dao;


import com.codecool.product.Product;

import java.util.List;

public interface ProductDAO {
        public Product getProduct(String product_Id);
        public List<Product> getAllProducts();
        public void updateProduct(Product productToUpdate);
        public void addCustomer(Product newProduct);
        public void deleteCustomer(String product_Id);

}
