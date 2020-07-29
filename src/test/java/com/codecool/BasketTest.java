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
        product1 = new Product("1", "apple", 10, 10);
        product2 = new Product("2", "banana", 15, 7);
    }

    @Test
    public void testBasket(){
        basket.addProduct(product1, 1);
        basket.addProduct(product2, 2);
        int expected = 40; // 1 * 10 + 2 * 15
        int actual = basket.getTotalBasketValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteProduct(){
        basket.addProduct(product1, 10);
        basket.removeProductFromBasket(product1);
        int expected = basket.getTransactionProducts().size();
        int actual = 0;
        assertEquals(expected, actual);
    }
}