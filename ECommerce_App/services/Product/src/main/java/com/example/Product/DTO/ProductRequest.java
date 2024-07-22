package com.example.Product.DTO;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(


        Integer id,

        @NotNull(message = "Product name is required")
        String name,

        @NotNull(message = "Product description is required")
        String description,

        @NotNull(message = "Product price should be positive")
        BigDecimal price,

        @NotNull(message = "quanity should be positive")
        double availableQuantity,

        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
