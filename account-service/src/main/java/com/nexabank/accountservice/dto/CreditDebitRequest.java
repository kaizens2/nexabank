package com.nexabank.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Request body for credit or debit operations on an account")
public record CreditDebitRequest(

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than Zero")
        @Schema(description = "The amount to credit or debit", example = "100.00")
        BigDecimal amount,

        @Schema(description = "Optional reference/note for this operation", example = "Salary credited")
        String refernce
) {
}
