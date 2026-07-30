package com.nexabank.accountservice.controller;

import com.nexabank.accountservice.dto.AccountResponse;
import com.nexabank.accountservice.dto.OpenAccountRequest;
import com.nexabank.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
