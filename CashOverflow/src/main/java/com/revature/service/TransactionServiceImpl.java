package com.revature.service;

import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.BankAccountRepo;
import com.revature.dao.TransactionRepo;
import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	TransactionRepo tranRepo;
	BankAccountRepo bankRepo;
	private ModelMapper mapper;

	@Autowired
	public TransactionServiceImpl(TransactionRepo tranRepo, ModelMapper mapper, BankAccountRepo bankRepo) {
		this.bankRepo = bankRepo;
		this.tranRepo = tranRepo;
		this.mapper = mapper;
	}

	private Transaction convertToEntity(TransactionDto dto) {
		return mapper.map(dto, Transaction.class);
	}

	/**
	 * Takes the dto object checks for transaction type and account overdraft,
	 * throws necessary errors and calls update balance.
	 * 
	 * @param Takes the DTO object from the endpoint
	 * @author Cameron, Amir, Chandra
	 */
	@Override
	public void addTransaction(TransactionDto dto) {
		BankAccount acc = bankRepo.getById(dto.getAccountId());
		
		Transaction transaction = convertToEntity(dto);
		if (transaction.getTxTypeId() == 1) {
			if (dto.getAmount() > acc.getBalance()) {
				throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Insufficient account balance");
			}
			updateBalance(-1 * transaction.getAmount(), acc);
		} else {
			updateBalance(transaction.getAmount(), acc);
		}
		transaction.setCreationDate(Instant.now());
		tranRepo.save(transaction);
	}

	/**
	 * Takes the amount and bank account to adjust the new balance then writes to
	 * the database.
	 * 
	 * @param Takes the amount of a transaction and the bank account object to
	 *              adjust
	 * @author Cameron, Amir, Chandra
	 */
	@Override
	public void updateBalance(double adjustment, BankAccount acc) {
		double newBalance = acc.getBalance() + adjustment;
		acc.setBalance(newBalance);
		bankRepo.save(acc);
	}

	/**
	 * Gets a list of transactions associated with a specific bank account.
	 * 
	 * @return List of transactions
	 * @param a bank account's id
	 * @author Cameron, Amir, Chandra
	 */
	@Override
	public List<Transaction> getTransactions(Integer bkId) {
		return tranRepo.findAllByAccountIdOrderByCreationDateDesc(bkId);
	}
}
