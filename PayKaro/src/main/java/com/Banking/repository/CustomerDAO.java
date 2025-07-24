package com.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.model.Customer;
import com.Banking.model.Wallet;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

	Customer findByMobileNo(final String mobileNo);

	Customer findByWallet(final Wallet wallet);
}
