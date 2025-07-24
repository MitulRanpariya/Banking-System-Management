package com.Banking.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banking.exception.BillPaymentException;
import com.Banking.exception.CustomerException;
import com.Banking.exception.WalletException;
import com.Banking.model.BillPayment;
import com.Banking.security.JwtUtil;
import com.Banking.service.BillPaymentService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class BillPaymentController {

	@Autowired
	private BillPaymentService billPaymentService;

	@Autowired
	private JwtUtil jwtUtil;

	private String extractMobileNoFromToken(final HttpServletRequest request) {
		val authorizationHeader = request.getHeader("Authorization");
		val token = authorizationHeader.substring(7);
		return jwtUtil.extractUsername(token);
	}

	// Add a new bill payment
	@PostMapping("/bills")
	public ResponseEntity<BillPayment> addBillPaymentHandler(
			@RequestBody final BillPayment billPayment,
			final HttpServletRequest request
	) throws BillPaymentException, CustomerException, WalletException {

		val mobileNo = extractMobileNoFromToken(request);
		val savedBillPayment = billPaymentService.addBillPayment(billPayment, mobileNo);
		return new ResponseEntity<>(savedBillPayment, HttpStatus.CREATED);
	}

	// View all bill payments for the logged-in customer
	@GetMapping("/bills")
	public ResponseEntity<List<BillPayment>> viewAllBillPaymentsHandler(
			final HttpServletRequest request
	) throws CustomerException, WalletException, BillPaymentException {

		val mobileNo = extractMobileNoFromToken(request);
		val billPayments = billPaymentService.viewAllBillPayments(mobileNo);
		return new ResponseEntity<>(billPayments, HttpStatus.OK);
	}
}
