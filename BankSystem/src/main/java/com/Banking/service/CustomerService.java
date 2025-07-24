package com.Banking.service;

import com.Banking.exception.CustomerException;
import com.Banking.model.Customer;

public interface CustomerService {

	Customer createCustomer(final Customer customer) throws CustomerException;

	Customer updateCustomer(final Customer customer, final String mobileNo) throws CustomerException;

	Customer getCustomerByMobileNo(final String mobileNo) throws CustomerException;
}
