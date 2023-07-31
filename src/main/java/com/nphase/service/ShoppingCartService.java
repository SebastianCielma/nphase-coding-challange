package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartService {
    private static final int BULK_DISCOUNT_QUANTITY = 3;
    private static final BigDecimal BULK_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(0.10);

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        Map<String, Integer> categoryQuantities = new HashMap<>();

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : shoppingCart.getProducts()) {
            BigDecimal productTotalPrice = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));

            int currentCategoryQuantity = categoryQuantities.getOrDefault(product.getCategory(), 0) + product.getQuantity();
            categoryQuantities.put(product.getCategory(), currentCategoryQuantity);

            if (currentCategoryQuantity > BULK_DISCOUNT_QUANTITY) {
                BigDecimal discountAmount = product.getPricePerUnit().multiply(BULK_DISCOUNT_PERCENTAGE).multiply(BigDecimal.valueOf(product.getQuantity()));
                productTotalPrice = productTotalPrice.subtract(discountAmount);
            }

            totalPrice = totalPrice.add(productTotalPrice);
        }

        return totalPrice;
    }
}
