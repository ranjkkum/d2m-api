package com.oracle.d2mapi1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.d2mapi1.model.Transaction;
import com.oracle.d2mapi1.repository.TransactionRepository;


@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}
	
	public Iterable<Transaction> getTransactionHistory() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getTransaction(Long id) {
		return transactionRepository.findById(id);
	}
}