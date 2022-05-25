package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.TransactionRepo;
import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;
import com.revature.service.TransactionService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class IncomeExpenseControllerTest {
	
	@Mock
	private static TransactionService txServ;
	
	private static IncomeExpenseController lou;
	
	@BeforeAll
	static void init() {
		lou = new IncomeExpenseController(txServ);
	}

	@Test
	void negativeTransactionTest() {
		TransactionDto dto = new TransactionDto(1, 1, -100.00, "SOS", Instant.now());
		
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto));
	}

	@Test
	void badRequestTest() {
		TransactionDto dto = new TransactionDto(0, 2, 1.00, "", Instant.now());
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto));
		TransactionDto dto1 = new TransactionDto(1, 2, null, "", Instant.now());
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto1));
		TransactionDto dto2 = new TransactionDto(1, 2, 1.00, null, Instant.now());
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto2));
		
	}
	
	@Test
	void addTransaction() {
		TransactionService transServ = Mockito.mock(TransactionService.class);
		Transaction transaction = Mockito.mock(Transaction.class);
		TransactionDto transDto = Mockito.mock(TransactionDto.class);
		IncomeExpenseController inExpCont = Mockito.mock(IncomeExpenseController.class);
		
		transaction.setAccountId(4);
		transaction.setAmount(5.00);
		transaction.setDescription("something");
		transaction.setTxTypeId(2);
		
		transDto.setAccountId(4);
		transDto.setAmount(5.00);
		transDto.setDescription("adding funds");
		transDto.setTxTypeId(2);
		
		
		inExpCont.addTransaction(transDto);
	}
	
	@Test
	void getTransactions() {
		TransactionService transServ = Mockito.mock(TransactionService.class);
		BankAccount bankAccount = Mockito.mock(BankAccount.class);
		TransactionRepo transRepo = Mockito.mock(TransactionRepo.class);
		IncomeExpenseController incExpCont = Mockito.mock(IncomeExpenseController.class);
		Transaction transaction = Mockito.mock(Transaction.class);
		
		bankAccount.setId(1);
		
		//when(transRepo.getById(1)).thenReturn(transaction);
		//wrong
		//when(transServ.getTransactions(1)).thenReturn(transRepo.findAllByAccountIdOrderByCreationDateDesc(1));
		//wrong
		when()
		incExpCont.getTransactions(1);
	}
}
