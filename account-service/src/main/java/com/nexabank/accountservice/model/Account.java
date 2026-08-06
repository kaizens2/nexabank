package com.nexabank.accountservice.model;

import com.nexabank.accountservice.enums.EnumAccountStatus;
import com.nexabank.accountservice.enums.EnumAccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EnumAccountType accountType = EnumAccountType.SAVING;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal balance =  BigDecimal.ZERO;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    @Builder.Default
    private EnumAccountStatus status = EnumAccountStatus.ACTIVE;

    @Version
    @Column(nullable = false)
    private Long version;

}
