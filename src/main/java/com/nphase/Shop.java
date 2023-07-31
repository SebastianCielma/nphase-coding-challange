package com.nphase;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import com.nphase.service.ShoppingCartService;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Shop {
    public static void main(String[] args) {
        Product tea = new Product("tea", new BigDecimal("5"), 5);
        Product coffee = new Product("coffee", new BigDecimal("3.5"), 3);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(tea);
        productList.add(coffee);

        ShoppingCart shoppingCart = new ShoppingCart(productList);

        ShoppingCartService shoppingCartService = new ShoppingCartService();

        BigDecimal totalPrice = shoppingCartService.calculateTotalPrice(shoppingCart);

    }
}
