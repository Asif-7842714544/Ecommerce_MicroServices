package com.asif.Order.Mapper;

import com.asif.Order.DTO.OrderRequest;
import com.asif.Order.Entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
    return Order.
            builder()
            .orderId(request.Id())
            .customerId(request.customerId())
            .reference(request.reference())
            .totalAmount(request.amount())
            .paymentMethod(request.paymentMethod())
            .build();
    }
}
