package com.nexabank.customerservice.service.impl;

import com.nexabank.customerservice.dto.CustomerRequest;
import com.nexabank.customerservice.dto.CustomerResponse;
import com.nexabank.customerservice.entity.Customer;
import com.nexabank.customerservice.exception.custom.UserAlreadyExistException;
import com.nexabank.customerservice.repository.CustomerRepository;
import com.nexabank.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public @NonNull CustomerResponse createCustomer(@NonNull CustomerRequest customerRequest) {
        // Implementation for creating a customer
        Optional<Customer> customer = customerRepository.findByEmail(customerRequest.email());

        // This throws an exception if present, breaking the execution flow
        customer.ifPresent(c -> {
            throw new UserAlreadyExistException("User already exists with this email: " + c.getEmail());
        });

        // This code only runs if the customer does NOT exist
        return CustomerResponse.from(customerRepository.save(Customer.builder()
                .email(customerRequest.email())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .phone(customerRequest.phone())
                .build()));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
