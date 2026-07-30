package com.nexabank.accountservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

public interface CustomerClient {

    @GetExchange("/nexabank/api/customers/{id}")
    CustomerDto getCustomerById(@PathVariable("id") UUID id);
}
