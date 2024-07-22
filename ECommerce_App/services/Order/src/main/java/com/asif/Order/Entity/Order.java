package com.asif.Order.Entity;


import com.asif.Order.DTO.OrderLine;
import com.asif.Order.DTO.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderId;
    private String reference;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String customerId;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate createdDate;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDate lastModifiedDate;


}
