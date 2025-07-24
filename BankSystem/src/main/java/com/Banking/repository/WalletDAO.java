package com.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.model.Wallet;

@Repository
public interface WalletDAO extends JpaRepository<Wallet, Integer> {
    // Add custom methods here if needed in future
}
