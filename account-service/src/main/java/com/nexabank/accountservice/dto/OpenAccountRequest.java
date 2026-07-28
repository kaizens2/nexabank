package com.nexabank.accountservice.dto;

import com.nexabank.accountservice.enums.EnumAccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Request payload for opening a new account")
public record OpenAccountRequest(
        @Schema(description = "The customer ID for whom to open an account")
        @NotNull(message = "Customer ID is not null")
        UUID customerId,

        @NotNull(message = "Account type is required")
        @Schema(description = "The type of account to open", example = "SAVINGS")
        EnumAccountType accountType,

        @NotNull(message = "Currency is required")
        @Schema(description = "The currency of the account", example = "INR")
        String currency
) {
}
