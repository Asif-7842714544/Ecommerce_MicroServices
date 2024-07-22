package com.asif.Ecommerce.Controller;

import com.asif.Ecommerce.DTO.CustomerRequest;
import com.asif.Ecommerce.DTO.CustomerResponse;
import com.asif.Ecommerce.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest custmerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(custmerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(@RequestBody @Valid CustomerResponse response) {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean> isCustomerExist(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.isCustomerExist(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> customerById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
