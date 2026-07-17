package com.nexabank.customerservice.service;

import com.nexabank.customerservice.dto.CustomerRequest;
import com.nexabank.customerservice.dto.CustomerResponse;
import com.nexabank.customerservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomer();
}
