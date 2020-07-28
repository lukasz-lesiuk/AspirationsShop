package com.codecool;

import com.codecool.shopping.basket.Basket;
import com.codecool.product.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasketTest {
    static Basket basket;
    static Product product1;
    static Product product2;

    @Before
    public void stepUp(){
        basket = new Basket();
        product1 = new Product();
        product2 = new Product();
    }

    @Test
    public void testBasket(){
        basket.addProduct(product1, 1);
        basket.addProduct(product2, 2);
        basket.addProduct(product1, 1);
        basket.addProduct(product1); //overloaded method should plus 1
        int expected = 5;
        int actual = basket.getTotalBasketValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteProduct(){
        basket.addProduct(product1);
        basket.deleteProduct(product1);
        int expected = basket.getTransactionProducts().size();
        int actual = 0;
        assertEquals(expected, actual);
    }
}