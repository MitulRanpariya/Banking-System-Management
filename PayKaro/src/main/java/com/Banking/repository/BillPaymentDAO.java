package com.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.model.BillPayment;
import com.Banking.model.Wallet;

import java.util.List;

@Repository
public interface BillPaymentDAO extends JpaRepository<BillPayment, Integer> {

	List<BillPayment> findByWallet(final Wallet wallet);
}
