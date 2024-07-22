package com.example.Product.DTO;

import java.math.BigDecimal;

public record ProductPurchaseResponse(

        Integer productId,
        String productName,
        String productDdescription,
        BigDecimal price,
        double quantity
) {
}
