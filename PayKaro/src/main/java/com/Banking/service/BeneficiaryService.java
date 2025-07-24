package com.Banking.service;

import java.util.List;

import com.Banking.dto.BeneficiaryDTO;
import com.Banking.exception.AccountException;
import com.Banking.exception.BeneficiaryException;
import com.Banking.exception.CustomerException;
import com.Banking.model.Beneficiary;

public interface BeneficiaryService {

	// Add beneficiary using DTO (safe validated)
	Beneficiary addBeneficiary(final String mobileNo, final BeneficiaryDTO beneficiaryDTO)
			throws CustomerException, AccountException;

	List<Beneficiary> getBeneficiaries(final String mobileNo) throws CustomerException;

	void deleteBeneficiary(final String mobileNo, final Integer beneficiaryId)
			throws CustomerException, BeneficiaryException;

	Beneficiary getBeneficiaryByIdAndCustomer(final String mobileNo, final Integer beneficiaryId)
			throws CustomerException, BeneficiaryException;
}
