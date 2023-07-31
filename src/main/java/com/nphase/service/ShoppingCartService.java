package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartService {
    private final int bulkDiscountQuantity;
    private final BigDecimal bulkDiscountPercentage;

    public ShoppingCartService(int bulkDiscountQuantity, BigDecimal bulkDiscountPercentage) {
        this.bulkDiscountQuantity = bulkDiscountQuantity;
        this.bulkDiscountPercentage = bulkDiscountPercentage;
    }

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        Map<String, Integer> categoryQuantities = new HashMap<>();

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : shoppingCart.getProducts()) {
            BigDecimal productTotalPrice = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));

            int currentCategoryQuantity = categoryQuantities.getOrDefault(product.getCategory(), 0) + product.getQuantity();
            categoryQuantities.put(product.getCategory(), currentCategoryQuantity);

            if (currentCategoryQuantity > bulkDiscountQuantity) {
                BigDecimal discountAmount = product.getPricePerUnit()
                        .multiply(bulkDiscountPercentage)
                        .multiply(BigDecimal.valueOf(product.getQuantity()));
                productTotalPrice = productTotalPrice.subtract(discountAmount);
            }

            totalPrice = totalPrice.add(productTotalPrice);
        }

        return totalPrice;
    }
}
