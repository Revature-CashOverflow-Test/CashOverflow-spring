package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class LoginRequestDtoTest {
	
	@Mock
	LoginRequestDto dto = new LoginRequestDto(null, null);
	
	@Test
	void testUsername() {
		String username = "Alec";
		dto.setUsername("Alex");
		assertEquals("Alex", dto.getUsername());
	}
	
	@Test
	void testPassword() {
		String password = "password";
		dto.setPassword("not password");
		assertEquals("not password", dto.getPassword());
	}

}
