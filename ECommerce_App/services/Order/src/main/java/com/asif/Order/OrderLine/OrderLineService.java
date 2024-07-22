package com.asif.Order.OrderLine;

import com.asif.Order.DTO.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;


    public Integer saverOrderLine(OrderLineRequest request) {
        OrderLine orderLine = mapper.toOrderLine(request);
        return orderLineRepository.save(orderLine).getId();
    }
}
