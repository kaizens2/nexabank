package com.nexabank.accountservice.exceptions.custom;

public class AccountNumberNotFoundException extends RuntimeException{
    public AccountNumberNotFoundException(String message) {
        super(message);
    }
}
