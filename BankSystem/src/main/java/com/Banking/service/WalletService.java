package com.Banking.service;

import com.Banking.exception.AccountException;
import com.Banking.exception.CustomerException;
import com.Banking.exception.WalletException;
import com.Banking.model.Beneficiary;
import com.Banking.model.Customer;

public interface WalletService {

	Customer showBalance(final String mobileNo) throws CustomerException;

	// Transfer from wallet to any normal account
	void transferWalletToAccount(
			final String sourceMobileNo,
			final Integer targetAccountId,
			final Double amount
	) throws CustomerException, AccountException, WalletException;

	// Transfer from wallet to any registered beneficiary
	void transferWalletToBeneficiary(
			final String mobileNo,
			final Beneficiary beneficiary,
			final Double amount
	) throws CustomerException, WalletException, AccountException;
}
