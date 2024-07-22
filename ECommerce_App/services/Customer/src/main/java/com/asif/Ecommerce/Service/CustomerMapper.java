package com.asif.Ecommerce.Service;

import com.asif.Ecommerce.DTO.CustomerRequest;
import com.asif.Ecommerce.DTO.CustomerResponse;
import com.asif.Ecommerce.Entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest custmerRequest) {
        if (custmerRequest == null) return null;
        return Customer.builder()
                .customerId(custmerRequest.id())
                .firstName(custmerRequest.firstName())
                .lastName(custmerRequest.lastName())
                .email(custmerRequest.email())
                .address(custmerRequest.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) return null;
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
