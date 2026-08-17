package com.nexabank.transactionservice.dto;

import com.nexabank.transactionservice.domain.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Schema(description = "Transaction ledger entry")
public record TransactionResponse(
        UUID id,
        String accountNumber,
        String direction,
        BigDecimal amount,
        String currency,
        String reference,
        String status,
        Map<String, Object> metadata,
        BigDecimal balanceAfter,
        LocalDateTime createdAt
) {
    public static TransactionResponse from(Transaction t) {
        return new TransactionResponse(
                t.getId(),
                t.getAccountNumber(),
                t.getDirection().name(),
                t.getAmount(),
                t.getCurrency(),
                t.getReference(),
                t.getStatus().name(),
                t.getMetadata(),
                t.getBalanceAfter(),
                t.getCreatedAt()
        );
    }
}