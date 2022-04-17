package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.BankAccountRepo;
import com.revature.dao.RequestRepo;
import com.revature.dao.TransactionRepo;
import com.revature.model.BankAccount;
import com.revature.model.FundTransfer;
import com.revature.model.UserAccount;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

	@Mock
	private BankAccountRepo dao;
	
	@Mock
	private TransactionRepo txDao;

	private BankAccountService serv;
	
	@Mock
	private UserAccountService userAccServ;
	
	@Mock
	private RequestRepo reqRepo;
	
	@BeforeEach
	void setUp() throws Exception {
		serv = new BankAccountServiceImpl(dao, txDao, userAccServ, reqRepo);
	}

	/**
	 * This method tests the BankAccountService method createAccount. It should
	 * return a BankAccount object with balance set to zero.
	 */
	@Test
	void createAccountTest() {
		UserAccount initialTestUser = new UserAccount(1000, "testuseremail@emailprovider.com", "testUserUsername",
				"testUserFirstName", "testUserLastName", "testUserPassword", null);

		BankAccount initialTestBankAccount = new BankAccount("myBankAccountName", "myBankAccountDescription", null, 1,
				initialTestUser);
		initialTestBankAccount.setBalance(2000000.0);
		UserAccount expectedTestUser = new UserAccount(1000, "testuseremail@emailprovider.com", "testUserUsername",
				"testUserFirstName", "testUserLastName", "testUserPassword", null);

		BankAccount expectedTestBankAccount = new BankAccount("myBankAccountName", "myBankAccountDescription", null, 1,
				expectedTestUser);
		expectedTestBankAccount.setBalance(0.0);
		// sending BankAccount object with balance US$2,000,000.00
		when(dao.save(initialTestBankAccount)).thenReturn(initialTestBankAccount);
		BankAccount test = serv.createAccount(initialTestBankAccount);
		verify(dao, times(1)).save(initialTestBankAccount);
		// verifying if we got a saved to the database an account with balance US$0.00
		assertEquals(test.getBalance(), expectedTestBankAccount.getBalance());
	}

	/**
	 * This method tests the BankAccountService method getBankAccounts. This method
	 * should be returning a list of BankAccounts
	 */
	@Test
	void selectAllBankAccountsByUser() {

		UserAccount initialTestUser = new UserAccount(1, "testuseremail@emailprovider.com", "testUserUsername",
				"testUserFirstName", "testUserLastName", "testUserPassword", Instant.now());

		BankAccount initialTestBankAccount = new BankAccount("myBankAccountName", "myBankAccountDescription",
				Instant.now(), 1, initialTestUser);

		List<BankAccount> initialList = new ArrayList<>();
		initialList.add(initialTestBankAccount);
		List<BankAccount> expectedList = new ArrayList<>();
		expectedList.addAll(initialList);
		when(dao.findAllByUserId(1)).thenReturn(initialList);

		List<BankAccount> test = serv.getBankAccounts(1);

		verify(dao, times(1)).findAllByUserId(initialTestBankAccount.getUser().getId());
		assertEquals(expectedList, test);
	}

	/**
	 * This tests that transactions with fractional cents are being handled properly
	 * as well as transactions that exceed the account's balance
	 */
	@Test
	void transferFundsTest() {
		BankAccount initialAccount1 = new BankAccount();
		initialAccount1.setBalance(100.00);
		BankAccount initialAccount2 = new BankAccount();
		initialAccount2.setBalance(0.0);
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setTransferFromAccount("account1");
		fundTransfer.setTransferToAccount("account2");
		fundTransfer.setTransferAmount(12.3456);
		BankAccount expectedAccount1 = new BankAccount();
		expectedAccount1.setBalance(87.65);
		BankAccount expectedAccount2 = new BankAccount();
		expectedAccount2.setBalance(12.35);
		UserAccount user = new UserAccount();
		List<BankAccount> expectedAccounts = new ArrayList<BankAccount>();
		expectedAccounts.add(expectedAccount1);
		expectedAccounts.add(expectedAccount2);

		when(dao.saveAll(expectedAccounts)).thenReturn(null);
		when(serv.getBankAccount(user, "account1")).thenReturn(initialAccount1);
		when(serv.getBankAccount(user, "account2")).thenReturn(initialAccount2);

		List<BankAccount> test = serv.transferFunds(user, fundTransfer);

		verify(dao, times(1)).saveAll(expectedAccounts);
		assertEquals(expectedAccounts, test);

		fundTransfer.setTransferAmount(101.101);
		assertThrows(ResponseStatusException.class, () -> serv.transferFunds(user, fundTransfer));
	}
}
