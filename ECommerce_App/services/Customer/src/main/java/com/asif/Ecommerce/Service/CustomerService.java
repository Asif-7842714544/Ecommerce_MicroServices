package com.asif.Ecommerce.Service;

import com.asif.Ecommerce.DTO.CustomerRequest;
import com.asif.Ecommerce.DTO.CustomerResponse;
import com.asif.Ecommerce.Entity.Customer;
import com.asif.Ecommerce.Exception.CustomerNotFoundException;
import com.asif.Ecommerce.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getCustomerId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = customerRepository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException("cannot update customer :: No customer found with provided id :" + request.id()));

        mergeCustomer(customer, request);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean isCustomerExist(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("no customer found for customerId: " + customerId));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
        if (!customerRepository.findById(customerId).isPresent()) {
            throw new CustomerNotFoundException("No customer found with provided id :" + customerId);
        }
    }
}
