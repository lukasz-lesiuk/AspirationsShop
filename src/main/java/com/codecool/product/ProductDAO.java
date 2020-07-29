package com.codecool.product;


import com.codecool.product.Product;

import java.util.List;

public interface ProductDAO {
        public Product getProduct(String productNmae);
        public Product getProductByPhrase(String phrase);
        public List<Product> getAllProducts();
        public void updateProduct(Product productToUpdate);
        public void addProduct(Product newProduct);
        public void deleteProduct(String product_Id);

}
