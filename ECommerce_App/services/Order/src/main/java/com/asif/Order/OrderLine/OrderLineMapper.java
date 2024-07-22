package com.asif.Order.OrderLine;

import com.asif.Order.DTO.OrderLine;
import com.asif.Order.Entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder()
                        .orderId(request.orderId())
                        .build())
                .productId(request.productId())
                .build();
    }
}
