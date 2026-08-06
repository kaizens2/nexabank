package com.nexabank.accountservice.repository;

import com.nexabank.accountservice.enums.EnumAccountType;
import com.nexabank.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

   Optional<Account> findByAccountNumber(String accountNumber);
   List<Account> findByCustomerId(UUID customerId);

   @Query("SELECT a FROM Account a WHERE a.customerId = :customerId AND a.accountType = :accountType")
    Optional<Account> findByCustomerIdAndAccountType(UUID customerId, EnumAccountType accountType);
}
