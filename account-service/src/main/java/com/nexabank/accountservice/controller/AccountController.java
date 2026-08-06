package com.nexabank.accountservice.controller;

import com.nexabank.accountservice.dto.AccountResponse;
import com.nexabank.accountservice.dto.CreditDebitRequest;
import com.nexabank.accountservice.dto.OpenAccountRequest;
import com.nexabank.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    private final AccountService accountService;

    @PostMapping("open")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody OpenAccountRequest openAccountRequest) {
        return ResponseEntity.ok(accountService.openAccount(openAccountRequest));
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity<AccountResponse> getByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }

    @GetMapping("all/{accountNumber}")
    public ResponseEntity<List<AccountResponse>> getAllAccountsByCustomerId(@PathVariable UUID accountNumber) {
        return ResponseEntity.ok(accountService.getAccountsByCustomer(accountNumber));
    }

    @PostMapping("{accountNumber}/debit")
    public ResponseEntity<AccountResponse> debitAmountFromAccount (@PathVariable("accountNumber") String accountNumber, @Valid @RequestBody CreditDebitRequest creditDebitRequest) {
        return new ResponseEntity<>(accountService.debit(accountNumber, creditDebitRequest), HttpStatus.CREATED);
    }

    @PostMapping("{accountNumber}/credit")
    public ResponseEntity<AccountResponse> creditAmountFromAccount (@PathVariable("accountNumber") String accountNumber, @Valid @RequestBody CreditDebitRequest creditDebitRequest) {
        return new ResponseEntity<>(accountService.credit(accountNumber, creditDebitRequest), HttpStatus.CREATED);
    }


}
