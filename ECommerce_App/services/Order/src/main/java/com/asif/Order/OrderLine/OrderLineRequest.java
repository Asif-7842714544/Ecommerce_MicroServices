package com.asif.Order.OrderLine;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        Double quantity
) {
}
