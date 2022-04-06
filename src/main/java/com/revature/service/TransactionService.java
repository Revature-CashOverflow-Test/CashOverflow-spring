package com.revature.service;

import java.util.List;

import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;

public interface TransactionService {

	public void addTransaction(TransactionDto dto);

	public void updateBalance(double adjustment, BankAccount acc);

	public List<Transaction> getTransactions(Integer bkId);

}
