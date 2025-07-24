package com.Banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.model.Customer;
import com.Banking.model.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

    List<TransactionHistory> findByCustomer(final Customer customer);
}
