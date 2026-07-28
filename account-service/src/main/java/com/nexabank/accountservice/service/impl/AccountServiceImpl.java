package com.nexabank.accountservice.service.impl;

import com.nexabank.accountservice.dto.AccountResponse;
import com.nexabank.accountservice.dto.OpenAccountRequest;
import com.nexabank.accountservice.enums.EnumAccountStatus;
import com.nexabank.accountservice.generator.AccountNumberGenerator;
import com.nexabank.accountservice.model.Account;
import com.nexabank.accountservice.repository.AccountRepository;
import com.nexabank.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    public AccountResponse openAccount(OpenAccountRequest openAccountRequest) {
        String accountNumber = accountNumberGenerator.generateAccountNumber();

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .customerId(openAccountRequest.customerId())
                .balance(BigDecimal.ZERO)
                .accountType(openAccountRequest.accountType())
                .currency(openAccountRequest.currency())
                .status(EnumAccountStatus.ACTIVE)
                .build();

        // TODO we need too do call on customer servce to check customer is exist or not
        return AccountResponse.from(accountRepository.save(account));
    }

    @Override
    public AccountResponse getAccountByNumber(String accountNumber) {
        return null;
    }

    @Override
    public List<AccountResponse> getAccountsByCustomer(UUID customerId) {
        return List.of();
    }

    @Override
    public AccountResponse debit(String accountNumber, BigDecimal amount) {
        return null;
    }

    @Override
    public AccountResponse credit(String accountNumber, BigDecimal amount) {
        return null;
    }
}
