package com.asif.Order.DTO;

import com.asif.Order.Product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer Id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment method should be specified")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer id should be specified")
        @NotEmpty(message = "customer id should not be empty")
        @NotBlank(message = "customer id should not be blank")
        String customerId,

        @NotEmpty(message = "you should atleast purchase one product")
        List<PurchaseRequest> products

) {
}
