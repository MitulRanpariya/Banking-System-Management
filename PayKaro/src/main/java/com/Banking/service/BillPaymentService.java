package com.Banking.service;

import java.util.List;

import com.Banking.exception.BillPaymentException;
import com.Banking.exception.CustomerException;
import com.Banking.exception.WalletException;
import com.Banking.model.BillPayment;

public interface BillPaymentService {

	BillPayment addBillPayment(
			final BillPayment billPayment,
			final String mobileNo
	) throws BillPaymentException, CustomerException, WalletException;

	List<BillPayment> viewAllBillPayments(
			final String mobileNo
	) throws CustomerException, WalletException, BillPaymentException;
}
