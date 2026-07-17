package com.nexabank.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request payload to register new customer")
public record CustomerRequest(

        @NotBlank(message = "firstName cannot be null")
        @Schema(description = "First name of the customer",example = "John")
        String firstName,

        @NotBlank(message = "lastName cannot be null")
        @Schema(description = "Last name of the customer",example = "Doe")
        String lastName,

        @Email(message = "email must be a valid email address")
        @NotBlank(message = "email cannot be null")
        @Schema(description = "Email address of the customer",example = "john.doe@example.com")
        String email,

        @NotBlank(message = "phone cannot be null")
        @Schema(description = "Phone number of the customer",example = "+911234567890")
        String phone
) {}
