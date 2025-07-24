package com.Banking.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Banking.exception.CustomerException;
import com.Banking.exception.LoginException;
import com.Banking.model.*;
import com.Banking.repository.CustomerDAO;
import com.Banking.security.CustomUserDetailsService;
import com.Banking.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    //  REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody final Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        val wallet = new Wallet();
        wallet.setBalance(0.0);
        customer.setWallet(wallet);

        customerDAO.save(customer);
        return ResponseEntity.ok("User registered successfully");
    }

    //  LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginDTO loginDTO) throws LoginException {
        val customer = customerDAO.findByMobileNo(loginDTO.getMobileNo());
        if (customer == null) throw new LoginException("Mobile No not registered");

        if (!passwordEncoder.matches(loginDTO.getPassword(), customer.getPassword())) {
            throw new LoginException("Invalid Password");
        }

        val userDetails = userDetailsService.loadUserByUsername(customer.getMobileNo());
        val accessToken = jwtUtil.generateAccessToken(userDetails.getUsername());
        val refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtAuthResponse(accessToken, refreshToken));
    }

    // REFRESH TOKEN
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody final RefreshRequest request) {
        val refreshToken = request.getRefreshToken();
        val username = jwtUtil.extractUsername(refreshToken);

        if (jwtUtil.validateRefreshToken(refreshToken, username)) {
            val newAccessToken = jwtUtil.generateAccessToken(username);
            return ResponseEntity.ok(new JwtAuthResponse(newAccessToken, refreshToken));
        }

        return ResponseEntity.badRequest().body("Invalid refresh token");
    }

    //  LOGOUT (Stateless - handled on client)
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful. Please remove token on client side.");
    }

    //  DELETE CUSTOMER & CASCADE DATA
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(final HttpServletRequest request) throws CustomerException {
        val authorizationHeader = request.getHeader("Authorization");
        val token = authorizationHeader.substring(7);
        val mobileNo = jwtUtil.extractUsername(token);

        val customer = customerDAO.findByMobileNo(mobileNo);
        if (customer == null) throw new CustomerException("Customer not found");

        customerDAO.delete(customer);
        return ResponseEntity.ok("Customer and all related data deleted successfully");
    }
}
