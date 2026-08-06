package com.nexabank.accountservice.service.impl;

import com.nexabank.accountservice.client.CustomerClient;
import com.nexabank.accountservice.client.CustomerDto;
import com.nexabank.accountservice.dto.AccountResponse;
import com.nexabank.accountservice.dto.CreditDebitRequest;
import com.nexabank.accountservice.dto.OpenAccountRequest;
import com.nexabank.accountservice.enums.EnumAccountStatus;
import com.nexabank.accountservice.exceptions.custom.AccountNumberNotFoundException;
import com.nexabank.accountservice.exceptions.custom.CustomerNotFoundException;
import com.nexabank.accountservice.generator.AccountNumberGenerator;
import com.nexabank.accountservice.model.Account;
import com.nexabank.accountservice.repository.AccountRepository;
import com.nexabank.accountservice.service.AccountService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountNumberGenerator accountNumberGenerator;
    private final CustomerClient customerClient;

    @Override
    public AccountResponse openAccount(OpenAccountRequest openAccountRequest) {

        log.info("Open account request: {}", openAccountRequest);
        String accountNumber = accountNumberGenerator.generateAccountNumber();

        CustomerDto customerDto = customerClient.getCustomerById(openAccountRequest.customerId());

        if (customerDto == null) {
            log.error("Customer not found with id {}", openAccountRequest.customerId());
            throw new CustomerNotFoundException("Customer not found with ID: " + openAccountRequest.customerId());
        }
        if(!customerDto.active()) {
            log.error("Customer not active with id {}", openAccountRequest.customerId());
            throw new IllegalArgumentException("Cannot open account with inactive customer");
        }

        accountRepository.findByCustomerIdAndAccountType(openAccountRequest.customerId(), openAccountRequest.accountType()).ifPresent(account -> {
            log.info("Account {} already exists", openAccountRequest.customerId());
            throw new IllegalArgumentException("Account of type " + openAccountRequest.accountType() + " already exists for customer: " + openAccountRequest.customerId());
        });

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .customerId(openAccountRequest.customerId())
                .balance(BigDecimal.ZERO)
                .accountType(openAccountRequest.accountType())
                .currency(openAccountRequest.currency())
                .status(EnumAccountStatus.ACTIVE)
                .build();

        log.info("Account {} opened for customer {}", accountNumber, openAccountRequest.customerId());
        return AccountResponse.from(accountRepository.save(account));
    }

    @Override
    @Transactional(readOnly = true)
    public @NotNull AccountResponse getAccountByNumber(String accountNumber) {
        log.info("Get account by number: {}", accountNumber);
        Account accountResponse = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    accountNumberNotFoundLog(accountNumber);
                    return new AccountNumberNotFoundException("Account not found with ID: " + accountNumber);
                });
        return AccountResponse.from(accountResponse);
    }

    @Override
    public List<AccountResponse> getAccountsByCustomer(UUID customerId) {
        log.info("Get account by customer: {}", customerId);
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(AccountResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountResponse debit(String accountNumber, CreditDebitRequest creditDebitRequest) {
        log.info("Debit account: {}", accountNumber);
        BigDecimal amount = creditDebitRequest.amount();

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Amount is invalid: {}", amount);
            throw new IllegalArgumentException("Amount is invalid ");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    accountNumberNotFoundLog(accountNumber);
                    return new AccountNumberNotFoundException("Account not found with ID: " + accountNumber);
                });

        if( account.getBalance().doubleValue() < amount.doubleValue()) {
            log.error("Insufficient funds for account: {}", accountNumber);
            throw new IllegalArgumentException("Insufficient funds");
        }

        log.info("transaction refernce : {}", creditDebitRequest.toString());
        account.setBalance(account.getBalance().subtract(amount));
        log.info("Amount debited: {}", amount);
        return AccountResponse.from(account);
    }

    @Override
    public AccountResponse credit(String accountNumber, CreditDebitRequest creditDebitRequest) {
        log.info("Credit account: {}", accountNumber);
        BigDecimal amount = creditDebitRequest.amount();

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Amount is invalid: {}", amount);
            throw new IllegalArgumentException("Amount is invalid ");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    accountNumberNotFoundLog(accountNumber);
                    return new AccountNumberNotFoundException("Account not found with ID: " + accountNumber);
                });

        log.info("transaction refernce : {}", creditDebitRequest.toString());
        account.setBalance(account.getBalance().add(amount));
        log.info("Amount credited: {}", amount);
        return AccountResponse.from(account);
    }

    private void accountNumberNotFoundLog(String accountNumber) {
        log.error("Account not found with id {}", accountNumber);
    }
}
