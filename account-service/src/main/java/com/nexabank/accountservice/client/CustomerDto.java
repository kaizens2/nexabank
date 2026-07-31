package com.nexabank.accountservice.client;

import java.util.UUID;

public record CustomerDto(
    UUID customerId,
    String firstName,
    String lastName,
    String email,
    String kycStatus,
    boolean active
) {
}
