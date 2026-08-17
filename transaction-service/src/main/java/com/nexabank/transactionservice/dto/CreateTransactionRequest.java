package com.nexabank.transactionservice.dto;

import com.nexabank.transactionservice.enums.EnumsTransactionDirection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;

@Schema(description = "Request to record and execute a transaction")
public record CreateTransactionRequest(

        @NotBlank(message = "Account number is required")
        @Schema(example = "NXB0011000001000001")
        String accountNumber,

        @Schema(example = "CREDIT")
        @NotNull(message = "Transaction direction is required")
        EnumsTransactionDirection direction,

        @NotNull(message = "Transaction amount is required")
        @DecimalMin(value = "0.01", message = "Transaction amount must be a positive value")
        BigDecimal amount,

        @NotNull(message = "Currency is required")
        @Schema(description = "INR")
        String currency,

        @Schema(example = "Salary - August 2026")
        String reference,

        @Schema(description = "Optional extra data — device, channel, fraud flags, etc.")
        Map<String, Object> metadata
) {
}
