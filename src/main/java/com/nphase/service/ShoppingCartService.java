package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;

public class ShoppingCartService {
    private static final int BULK_DISCOUNT_QUANTITY = 3;
    private static final BigDecimal BULK_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(0.10);

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts().stream()
                .map(this::calculateProductTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateProductTotalPrice(Product product) {
        BigDecimal productTotalPrice = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));

        if (product.getQuantity() > BULK_DISCOUNT_QUANTITY) {
            BigDecimal discountAmount = product.getPricePerUnit().multiply(BULK_DISCOUNT_PERCENTAGE);
            productTotalPrice = productTotalPrice.subtract(discountAmount);
        }

        return productTotalPrice;
    }
}
