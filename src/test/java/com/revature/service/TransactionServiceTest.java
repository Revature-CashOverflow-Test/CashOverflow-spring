package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.BankAccountRepo;
import com.revature.dao.TransactionRepo;
import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	private TransactionService serv;

	@Mock
	private BankAccountRepo bankRepo;

	@Mock
	private TransactionRepo txRepo;

	@Autowired
	private ModelMapper mapper;


	@BeforeEach
	void setUpBeforeClass() throws Exception {
		serv = new TransactionServiceImpl(txRepo, mapper, bankRepo);
	}
	
	@Test
	void addTransactionTest() {
		BankAccount acc = new BankAccount(1,"Checking", 10.00, "SOS", Instant.now(), 1, null, null);
		TransactionDto dto = new TransactionDto(1, 1, 100.00, "SOS", Instant.now()); 
		when(bankRepo.getById(1)).thenReturn(acc);
		assertThrows(ResponseStatusException.class, () -> serv.addTransaction(dto));

	}

}
