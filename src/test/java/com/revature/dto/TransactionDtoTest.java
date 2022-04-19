package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TransactionDtoTest {
	
	@Mock
	TransactionDto dto = new TransactionDto(0, 0, null, null, null);
	
	@Test
	void testAccountId() {
		int accountId = 1;
		dto.setAccountId(2);
		assertEquals(2, dto.getAccountId());
	}
	
	@Test
	void testTxTypeId() {
		int txTypeId = 1;
		dto.setTxTypeId(2);
		assertEquals(2, dto.getTxTypeId());	
	}
	
	@Test
	void testAmount() {
		double amount = 10.00;
		dto.setAmount(20.00);
		assertEquals(20.00, dto.getAmount());
	}
	
	@Test
	void testDescription() {
		String description = "beer money";
		dto.setDescription("fun money");
		assertEquals("fun money", dto.getDescription());
	}
	
//	@Test
////	public void testCreationDate() {
//		/**
//		 * TODO: Test Instant Type?
//		 */
//	}

}
