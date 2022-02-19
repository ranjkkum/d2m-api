package com.oracle.d2mapi1.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.d2mapi1.exceptions.InvalidTransationReferenceException;
import com.oracle.d2mapi1.model.Transaction;
import com.oracle.d2mapi1.service.TransactionService;



@RestController
public class TransactionController {

	@Autowired
	Environment environment;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/transaction")
	public Long createTransaction(@RequestBody Transaction transaction) {
		transactionService.saveTransaction(transaction);
		return transaction.getTransactionId();
	}
	
	@GetMapping("/transaction")
	public Iterable<Transaction> viewAllTransactions() {
		return transactionService.getTransactionHistory();
	}
	
	@GetMapping("/health")
	public String getType() throws UnknownHostException {
		
		return "Hello from API  Server !! "
				+ InetAddress.getLocalHost().getHostAddress() + " | " 
				+ InetAddress.getLocalHost().getHostName()+ " | "
				+ InetAddress.getLoopbackAddress().getHostAddress() + "|"
				+ InetAddress.getLoopbackAddress().getHostName();
	}
	
	@GetMapping("/health1")
	public String getType1() throws UnknownHostException {
		
		return "Hello from API  Server !! "
				+ InetAddress.getLocalHost().getHostAddress() + " | " 
				+ InetAddress.getLocalHost().getHostName()+ " | "
				+ InetAddress.getLoopbackAddress().getHostAddress() + "|"
				+ InetAddress.getLoopbackAddress().getHostName();
	}
	
	@GetMapping("/transaction/{id}")
	public Transaction viewTransactionById(@PathVariable ("id") Long id) {
		Optional<Transaction> transaction = transactionService.getTransaction(id);
		if(transaction.isPresent()) {
			return transaction.get();
		}
		
		throw new InvalidTransationReferenceException("Invalid transaction reference provided");
	}
}