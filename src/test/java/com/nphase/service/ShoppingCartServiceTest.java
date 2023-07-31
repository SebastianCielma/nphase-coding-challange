package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {

    @Test
    public void calculatesPrice()  {
        int bulkDiscountQuantity = 3;
        BigDecimal bulkDiscountPercentage = BigDecimal.valueOf(0.10);

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 2,"drinks"),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1,"drinks")
        ));

        ShoppingCartService service = new ShoppingCartService(bulkDiscountQuantity, bulkDiscountPercentage);
        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(11.5));
    }

}