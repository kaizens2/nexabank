package com.nexabank.customerservice.service;

import com.nexabank.customerservice.dto.CustomerRequest;
import com.nexabank.customerservice.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
}
