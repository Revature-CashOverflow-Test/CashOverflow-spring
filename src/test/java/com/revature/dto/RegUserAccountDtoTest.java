package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class RegUserAccountDtoTest {
	
	@Mock
	RegUserAccountDto dto = new RegUserAccountDto(null, null, null, null, null);
	
	@Test
	void testEmail() {
		String email = "dog@gmail.com";
		dto.setEmail("cat@gmail.com");
		assertEquals("cat@gmail.com", dto.getEmail());
	}
	
	@Test
	void testUsername() {
		String username = "bigBenz";
		dto.setUsername("biggerBenz");
		assertEquals("biggerBenz", dto.getUsername());
	}
	
	@Test
	void testFirstName() {
		String firstName = "Sarah";
		dto.setFirstName("Abby");
		assertEquals(dto.getFirstName(), "Abby");
	}
	
	@Test
	void testLastName() {
		String lastName = "Johnson";
		dto.setLastName("Jefferson");
		assertEquals("Jefferson", dto.getLastName());
	}
	
	@Test
	void testPassword() {
		String password = "letsgo";
		dto.setPassword("NoGo");
		assertEquals("NoGo", dto.getPassword());
	}



}
