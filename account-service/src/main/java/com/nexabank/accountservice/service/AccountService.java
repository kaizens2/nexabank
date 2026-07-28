package com.nexabank.accountservice.service;

import com.nexabank.accountservice.dto.AccountResponse;
import com.nexabank.accountservice.dto.OpenAccountRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

   AccountResponse openAccount(OpenAccountRequest openAccountRequest);
   AccountResponse getAccountByNumber(String accountNumber);
   List<AccountResponse> getAccountsByCustomer(UUID customerId);
   AccountResponse debit(String accountNumber, BigDecimal amount);
   AccountResponse credit(String accountNumber, BigDecimal amount);
}
