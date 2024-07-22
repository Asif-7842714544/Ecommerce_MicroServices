package com.asif.Order.OrderLine;

import com.asif.Order.DTO.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}
