package com.asif.Order.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Integer productId,
        @Positive(message = "quantity should be greater than zero")
        double quantity

) {
}
