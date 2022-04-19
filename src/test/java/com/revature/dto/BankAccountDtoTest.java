package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.yaml.snakeyaml.tokens.DocumentEndToken;

class BankAccountDtoTest {
	

	@Mock BankAccountDto dto = new BankAccountDto();
	
	@Test
	void testId() {
		int id = 2;
		dto.setId(1);
		assertEquals(1, dto.getId());		
	}
	
	@Test
	void testName() {
		String name = "Chuck Norris";
		dto.setName("Worst nightmare");
		assertEquals("Worst nightmare", dto.getName());			
	}
	
	@Test
	void testBalance() {
		Double balance = 68.99;
		dto.setBalance(21.00);
		assertEquals(21.00, dto.getBalance());
	}
	
	@Test
	void testDescription() {
		String description = "Money to ball";
		dto.setDescription("Rent");
		assertEquals("Rent", dto.getDescription());
	}
	
	@Test
	void testSetterCreationDate() {
		/**
		 * TODO: Test Instant type?
		 */
	}
	
	@Test
	void testAccountTypeId() {
		int accountTypeId = 1;
		dto.setAccountTypeId(2);
		assertEquals(2, dto.getAccountTypeId());
	}

}
