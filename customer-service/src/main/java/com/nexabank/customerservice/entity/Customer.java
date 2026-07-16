package com.nexabank.customerservice.entity;

import com.nexabank.customerservice.enums.EnumKYCStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private final UUID customerId;

    @Column(name = "first_name", nullable = false, length = 100)
    private final String firstName;

    @Column(name = "last_name", length = 100)
    private final String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private final String email;

    @Column(name = "phone", nullable = false, length = 13)
    private final String phone;

    @Column(name = "kyc_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private final EnumKYCStatus kycStatus = EnumKYCStatus.PENDING;

    @Column(name = "active", nullable = false)
    @Builder.Default
    private final boolean active = true;
}
