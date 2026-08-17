package com.nexabank.transactionservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDto(
        UUID id,
        String accountNumber,
        UUID customerId,
        String accountType,
        BigDecimal balance,
        String currency,
        String status
) {}