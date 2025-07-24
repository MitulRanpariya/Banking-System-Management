package com.Banking.service;

import java.util.List;

import com.Banking.exception.CustomerException;
import com.Banking.model.Customer;
import com.Banking.model.TransactionHistoryDTO;

public interface TransactionHistoryService {

    void recordTransaction(
            final Customer customer,
            final String type,
            final String sourceType,
            final Integer sourceId,
            final String targetType,
            final Integer targetId,
            final Double amount,
            final String description,
            final String senderMobileNo,
            final String senderAccountId
    );

    List<TransactionHistoryDTO> getAllTransactionsForCustomer(final String mobileNo) throws CustomerException;
}
