package com.nphase;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import com.nphase.service.ShoppingCartService;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Shop {
    public static void main(String[] args) {
        // Create Product objects
        Product tea = new Product("tea", new BigDecimal("5.3"), 2, "drinks");
        Product coffee = new Product("coffee", new BigDecimal("3.5"), 2, "drinks");
        Product cheese = new Product("cheese", new BigDecimal("8"), 2, "food");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(tea);
        productList.add(coffee);
        productList.add(cheese);
        ShoppingCart shoppingCart = new ShoppingCart(productList);

        ShoppingCartService shoppingCartService = new ShoppingCartService();
        BigDecimal totalPrice = shoppingCartService.calculateTotalPrice(shoppingCart);
    }
}

