package com.nexabank.accountservice.client;

import java.util.UUID;

public record CustomerDto(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String KycStatus,
    boolean active
) {
}
