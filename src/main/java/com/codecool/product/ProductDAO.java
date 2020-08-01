package com.codecool.product;


import com.codecool.product.Product;

import java.util.List;

public interface ProductDAO {
        public Product getProduct(String productNmae);
        public List<Product> getProductByPhrase(String phrase);
        public List<Product> getAllProducts();
        public List<Product> getProductByCategory(String categoryName);
        public void updateProductQuantity(Product productToUpdate,int newQuantity);
        public void addProduct(Product newProduct);
        public void deleteProduct(String product_Id);

}
