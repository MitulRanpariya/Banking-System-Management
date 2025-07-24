package com.Banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banking.dto.BeneficiaryDTO;
import com.Banking.exception.AccountException;
import com.Banking.exception.BeneficiaryException;
import com.Banking.exception.CustomerException;
import com.Banking.model.Account;
import com.Banking.model.Beneficiary;
import com.Banking.model.Customer;
import com.Banking.repository.AccountDAO;
import com.Banking.repository.BeneficiaryDAO;
import com.Banking.repository.CustomerDAO;

import lombok.val;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private BeneficiaryDAO beneficiaryDAO;

	@Autowired
	private AccountDAO accountDAO;

	@Transactional
	@Override
	public Beneficiary addBeneficiary(final String mobileNo, final BeneficiaryDTO beneficiaryDTO)
			throws CustomerException, AccountException {

		val sourceCustomer = this.customerDAO.findByMobileNo(mobileNo);
		if (sourceCustomer == null) {
			throw new CustomerException("Logged-in customer not found");
		}

		val targetCustomer = this.customerDAO.findByMobileNo(beneficiaryDTO.getTargetCustomerMobile());
		if (targetCustomer == null) {
			throw new CustomerException("Target customer not found");
		}

		val targetAccount = this.accountDAO.findById(beneficiaryDTO.getTargetAccountId())
				.orElseThrow(() -> new AccountException("Target account not found"));

		if (!targetAccount.getWallet().equals(targetCustomer.getWallet())) {
			throw new AccountException("Target account does not belong to the specified target customer");
		}

		val beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());
		beneficiary.setTargetCustomerMobile(beneficiaryDTO.getTargetCustomerMobile());
		beneficiary.setTargetAccountId(beneficiaryDTO.getTargetAccountId());
		beneficiary.setCustomer(sourceCustomer);

		return this.beneficiaryDAO.save(beneficiary);
	}

	@Override
	public List<Beneficiary> getBeneficiaries(final String mobileNo) throws CustomerException {
		val customer = this.customerDAO.findByMobileNo(mobileNo);
		if (customer == null) {
			throw new CustomerException("Customer not found");
		}
		return this.beneficiaryDAO.findByCustomer(customer);
	}

	@Transactional
	@Override
	public void deleteBeneficiary(final String mobileNo, final Integer beneficiaryId)
			throws CustomerException, BeneficiaryException {

		val customer = this.customerDAO.findByMobileNo(mobileNo);
		if (customer == null) {
			throw new CustomerException("Customer not found");
		}

		val beneficiary = this.beneficiaryDAO.findById(beneficiaryId)
				.orElseThrow(() -> new BeneficiaryException("Beneficiary not found"));

		if (!beneficiary.getCustomer().equals(customer)) {
			throw new BeneficiaryException("Beneficiary does not belong to the current user");
		}

		this.beneficiaryDAO.delete(beneficiary);
	}

	@Override
	public Beneficiary getBeneficiaryByIdAndCustomer(final String mobileNo, final Integer beneficiaryId)
			throws CustomerException, BeneficiaryException {

		val customer = this.customerDAO.findByMobileNo(mobileNo);
		if (customer == null) {
			throw new CustomerException("Customer not found");
		}

		val beneficiary = this.beneficiaryDAO.findById(beneficiaryId)
				.orElseThrow(() -> new BeneficiaryException("Beneficiary not found"));

		if (!beneficiary.getCustomer().equals(customer)) {
			throw new BeneficiaryException("Beneficiary does not belong to the current user");
		}

		return beneficiary;
	}
}
