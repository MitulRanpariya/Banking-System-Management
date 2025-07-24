package com.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.model.Beneficiary;
import com.Banking.model.Customer;

import java.util.List;

@Repository
public interface BeneficiaryDAO extends JpaRepository<Beneficiary, Integer> {

	List<Beneficiary> findByCustomer(final Customer customer);

}
