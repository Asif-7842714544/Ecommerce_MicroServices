package com.example.Product.DTO;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,
        String name,
        String description,
        BigDecimal price,
        double availableQuantity,

        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
