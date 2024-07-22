package com.asif.Order.Service;

import com.asif.Order.Customer.CustomerClient;
import com.asif.Order.Customer.CustomerResponse;
import com.asif.Order.DTO.OrderRequest;
import com.asif.Order.Entity.Order;
import com.asif.Order.Exception.BusinessException;
import com.asif.Order.Mapper.OrderMapper;
import com.asif.Order.OrderLine.OrderLineRequest;
import com.asif.Order.OrderLine.OrderLineService;
import com.asif.Order.Product.ProductClient;
import com.asif.Order.Product.PurchaseRequest;
import com.asif.Order.Product.PurchaseResponse;
import com.asif.Order.Repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
//    check the customer-->openFeign
        CustomerResponse customer = customerClient.
                findCustomerById(request.customerId()).
                orElseThrow(() -> new BusinessException("cannot create order:: No customer exist with id " + request.customerId()));

        log.info("customer: {}", customer.firstName());
//        purchase the orders-->product_ms(RestTemplate)

        List<PurchaseResponse> purchaseResponses = productClient.purchaseProduct(request.products());
        log.info("purchase Responses: {}", purchaseResponses);

//        persist order
        Order order = orderRepository.save(mapper.toOrder(request));

//        persist orderLine
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saverOrderLine(new OrderLineRequest(
                    null,
                    order.getOrderId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }
//        start paymentProcess

//        send the order Confirmation -->notification-ms(Kafka)

        return order.getOrderId();

    }
}
