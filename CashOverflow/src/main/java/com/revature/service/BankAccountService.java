package com.revature.service;

import java.util.List;

import com.revature.model.BankAccount;
import com.revature.model.FundTransfer;
import com.revature.model.UserAccount;

public interface BankAccountService {

	public BankAccount createAccount(BankAccount newAccount);

	public BankAccount getBankAccount(UserAccount user, String name);
	
	public List<BankAccount> getBankAccounts(int id);

	public List<BankAccount> transferFunds(UserAccount user, FundTransfer fundTransfer);

}
