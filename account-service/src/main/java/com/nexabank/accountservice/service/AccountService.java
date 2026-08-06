package com.nexabank.accountservice.service;

import com.nexabank.accountservice.dto.AccountResponse;
import com.nexabank.accountservice.dto.CreditDebitRequest;
import com.nexabank.accountservice.dto.OpenAccountRequest;

import java.util.List;
import java.util.UUID;

public interface AccountService {

   AccountResponse openAccount(OpenAccountRequest openAccountRequest);
   AccountResponse getAccountByNumber(String accountNumber);
   List<AccountResponse> getAccountsByCustomer(UUID customerId);
   AccountResponse debit(String accountNumber, CreditDebitRequest  creditDebitRequest);
   AccountResponse credit(String accountNumber, CreditDebitRequest creditDebitRequest);
}
