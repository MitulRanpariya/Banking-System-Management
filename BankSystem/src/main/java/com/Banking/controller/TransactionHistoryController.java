package com.Banking.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banking.exception.CustomerException;
import com.Banking.model.TransactionHistoryDTO;
import com.Banking.security.JwtUtil;
import com.Banking.service.TransactionHistoryService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/customers/transactions")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private JwtUtil jwtUtil;

    private String extractMobileNoFromToken(final HttpServletRequest request) {
        val authorizationHeader = request.getHeader("Authorization");
        val token = authorizationHeader.substring(7);
        return jwtUtil.extractUsername(token);
    }

    @GetMapping
    public ResponseEntity<List<TransactionHistoryDTO>> getAllTransactions(final HttpServletRequest request)
            throws CustomerException {
        val mobileNo = extractMobileNoFromToken(request);
        val transactions = transactionHistoryService.getAllTransactionsForCustomer(mobileNo);
        return ResponseEntity.ok(transactions);
    }
}
