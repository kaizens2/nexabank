package com.nexabank.accountservice.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component   // ← infrastructure utility, not a business "service"
@RequiredArgsConstructor
public class AccountNumberGenerator {

    private final JdbcTemplate jdbcTemplate;

    private static final String BANK_CODE = "NXB";
    private static final String BRANCH_CODE = "001";

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateAccountNumber() {
        jdbcTemplate.update(
                "UPDATE account_number_sequence SET next_val = next_val + 1 WHERE id = 1"
        );
        Long nextVal = jdbcTemplate.queryForObject(
                "SELECT next_val FROM account_number_sequence WHERE id = 1",
                Long.class
        );
        return BANK_CODE + BRANCH_CODE + String.format("%010d", nextVal);
    }
}