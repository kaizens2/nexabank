package com.nexabank.customerservice.dto;

import com.nexabank.customerservice.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;
@Schema(description = "Response payload for customer information")
public record CustomerResponse(
        @Schema(description = "Unique identifier for the customer", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID customerId,
        @Schema(description = "First name of the customer", example = "John")
        String firstName,
        @Schema(description = "Last name of the customer", example = "Doe")
        String lastName,
        @Schema(description = "Email address of the customer", example = "john.doe@example.com")
        String email,
        @Schema(description = "Phone number of the customer", example = "+911234567890")
        String phone,
        @Schema(description = "KYC status of the customer", example = "PENDING")
        String kycStatus,
        @Schema(description = "Indicates if the customer is active", example = "true")
        boolean active,
        @Schema(description = "Timestamp when the customer was created", example = "2023-10-01T10:00:00")
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
