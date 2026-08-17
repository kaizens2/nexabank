package com.nexabank.transactionservice.repository;

import com.nexabank.transactionservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByAccountNumberOrderByCreatedAtDesc(String accountNumber);

    List<Transaction> findByCustomerIdOrderByCreatedAtDesc(UUID customerId);
}
