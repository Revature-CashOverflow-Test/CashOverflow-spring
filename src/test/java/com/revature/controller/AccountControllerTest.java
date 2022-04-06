package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.service.BankAccountService;
import com.revature.service.UserAccountService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

	@Mock
	private BankAccountService bankServ;

	@Mock
	private UserAccountService accServ;

	@Mock
	private Authentication auth;

	@Mock
	private ModelMapper mapper;

	private AccountController cont;

	@BeforeEach
	void setUp() throws Exception {
		cont = new AccountController(bankServ, mapper, accServ);
	}

	@Test
	void createBankAccountTest() {
		BankAccount initialAccount = new BankAccount();
		initialAccount.setName("Awoo");
		initialAccount.setDescription("backend test account");
		initialAccount.setAccountTypeId(1);
		BankAccountDto dtoAccount = new BankAccountDto();
		dtoAccount.setName("Awoo");
		dtoAccount.setDescription("backend test account");
		dtoAccount.setAccountTypeId(1);
		BankAccountDto expectedAccount = new BankAccountDto();
		expectedAccount.setName("Awoo");
		expectedAccount.setDescription("backend test account");
		expectedAccount.setAccountTypeId(1);
		UserAccount mockUser = new UserAccount("Awoo", "hasdf");

		when(mapper.map(dtoAccount, BankAccount.class)).thenReturn(initialAccount);
		when(auth.getName()).thenReturn("^_^");
		when(accServ.getUserFromUsername("^_^")).thenReturn(mockUser);
		when(bankServ.createAccount(initialAccount)).thenReturn(initialAccount);
		when(mapper.map(initialAccount, BankAccountDto.class)).thenReturn(dtoAccount);

		BankAccountDto actualUser = cont.createBankAccount(auth, dtoAccount);

		verify(mapper, times(1)).map(dtoAccount, BankAccount.class);
		verify(auth, times(1)).getName();
		verify(accServ, times(1)).getUserFromUsername("^_^");
		verify(bankServ, times(1)).createAccount(initialAccount);
		verify(mapper, times(1)).map(initialAccount, BankAccountDto.class);

		assertEquals(actualUser, expectedAccount);
	}
}
