package com.nexabank.customerservice.dto;

import com.nexabank.customerservice.entity.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        String firstName,
        String lastName,
        String email,
        String phone,
        String kycStatus,
        boolean active,
        LocalDateTime createdAt
) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getKycStatus().name(),
                customer.isActive(),
                customer.getCreatedAt()
        );
    }
}
