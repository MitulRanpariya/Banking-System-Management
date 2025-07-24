package com.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.model.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {

    Account findByAccountNoAndIfscCode(final String accountNo, final String ifscCode);

}
