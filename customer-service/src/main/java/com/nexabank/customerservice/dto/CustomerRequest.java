package com.nexabank.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        @NotBlank(message = "firstName cannot be null")
        String firstName,

        @NotBlank(message = "lastName cannot be null")
        String lastName,

        @Email(message = "email must be a valid email address")
        @NotBlank(message = "email cannot be null")
        String email,

        @NotBlank(message = "phone cannot be null")
        String phone
) {}
