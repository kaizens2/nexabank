package com.nexabank.accountservice.dto;

import com.nexabank.accountservice.enums.EnumAccountStatus;
import com.nexabank.accountservice.enums.EnumAccountType;
import com.nexabank.accountservice.model.Account;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Account details required in API responses")
public record AccountResponse(

        @Schema(description = "The unique identifier for the account")
        UUID id,

        @Schema(description = "Bank account number", example = "NXB0011000001000001")
        String accountNumber,

        @Schema(description = "The type of the account")
        EnumAccountType accountType,

        @Schema(description = "The ID of the customer to whom the account belongs")
        UUID customerId,

        @Schema(description = "The balance of the account")
        BigDecimal balance,

        @Schema(description = "The currency of the account")
        String currency,

        @Schema(description = "The status of the account")
        EnumAccountStatus status,

        @Schema(description = "The date and time when the account was created")
        LocalDateTime createdAt
) {

    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getCustomerId(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus(),
                account.getCreatedAt()
        );
    }
}
