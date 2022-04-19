package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TransferBetweenDtoTest {
	
	@Mock
	TransferBetweenDto dto = new TransferBetweenDto();
	
	@Test
	void testFromAccount() {
		int fromAccount = 1;
		dto.setFromAccount(2);
		assertEquals(2, dto.getFromAccount());
	}
	
	@Test
	void testToAccount() {
		int toAccount = 2;
		dto.setToAccount(3);
		assertEquals(3, dto.getToAccount());
	}
	
	@Test
	void testAmount() {
		double amount = 10.00;
		dto.setAmount(20.00);
		assertEquals(20.00, dto.getAmount());
	}

}
