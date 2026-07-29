package com.nexabank.customerservice.service;

import com.nexabank.customerservice.dto.CustomerRequest;
import com.nexabank.customerservice.dto.CustomerResponse;
import com.nexabank.customerservice.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomer(UUID customerId);

    List<Customer> getAllCustomer();
}
