package com.Banking.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banking.exception.*;
import com.Banking.model.Beneficiary;
import com.Banking.model.Customer;
import com.Banking.security.JwtUtil;
import com.Banking.service.BeneficiaryService;
import com.Banking.service.WalletService;

@RestController
@RequestMapping("/customers/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	private BeneficiaryService beneficiaryService;

	@Autowired
	private JwtUtil jwtUtil;

	private String extractMobileNoFromToken(final HttpServletRequest request) {
		val authorizationHeader = request.getHeader("Authorization");
		val token = authorizationHeader.substring(7);
		return jwtUtil.extractUsername(token);
	}

	@GetMapping("/balance")
	public ResponseEntity<Customer> showBalanceHandler(final HttpServletRequest request) throws CustomerException {
		val mobileNo = extractMobileNoFromToken(request);
		val customer = walletService.showBalance(mobileNo);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/transferToAccount")
	public ResponseEntity<String> transferWalletToAccountHandler(
			@RequestParam final Integer targetAccountId,
			@RequestParam final Double amount,
			final HttpServletRequest request
	) throws CustomerException, WalletException, AccountException {
		val sourceMobile = extractMobileNoFromToken(request);
		walletService.transferWalletToAccount(sourceMobile, targetAccountId, amount);
		return ResponseEntity.ok("Amount transferred from wallet to target account successfully");
	}

	@PostMapping("/transferToBeneficiary")
	public ResponseEntity<String> transferToBeneficiary(
			@RequestParam final Integer beneficiaryId,
			@RequestParam final Double amount,
			final HttpServletRequest request
	) throws CustomerException, BeneficiaryException, WalletException, AccountException {
		val mobileNo = extractMobileNoFromToken(request);
		val beneficiary = beneficiaryService.getBeneficiaryByIdAndCustomer(mobileNo, beneficiaryId);
		walletService.transferWalletToBeneficiary(mobileNo, beneficiary, amount);
		return ResponseEntity.ok("Amount transferred from wallet to beneficiary successfully");
	}
}
