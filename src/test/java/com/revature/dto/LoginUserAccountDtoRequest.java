package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class LoginUserAccountDtoRequest {
	
	@Mock
	LoginUserAccountDto dto = new LoginUserAccountDto();
	
	@Test
	void testId() {
		int id = 2;
		dto.setId(3);
		assertEquals(3, dto.getId());
	}
	
	@Test
	void testUsername() {
		String username = "hudson_alec";
		dto.setUsername("ahhudson");
		assertEquals("ahhudson", dto.getUsername());
	}
	
	@Test 
	void testFirstName() {
		String firstName = "Alec";
		dto.setFirstName("Collin");
		assertEquals("Collin", dto.getFirstName());
	}
	
	@Test
	void testLastName() {
		String lastName = "Hudson";
		dto.setLastName("Jackson");
		assertEquals("Jackson", dto.getLastName());
	}
	
	@Test
	void testCreationDate() {
		/**
		 * TODO: test Instant type?
		 */
	}

}
