package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.revature.model.UserAccount;

class UserAccountDtoTest {
	
	@Mock
	UserAccount uAcc = new UserAccount();
	
	@Mock
	UserAccountDto dto = new UserAccountDto(uAcc);
	
	@Test
	void testId() {
		int id = 12;
		dto.setId(13);
		assertEquals(13, dto.getId());
	}
	
	@Test
	void testUsername()	 {
		String username = "hudsonalec";
		dto.setUsername("ahhudson");
		assertEquals("ahhudson", dto.getUsername());
	}
	
	@Test
	void testFirstName() {
		String firstName = "Alec";
		dto.setFirstName("joe");
		assertEquals("joe", dto.getFirstName());
	}
	
	@Test
	void testLastName() {
		String lastName = "flo";
		dto.setLastName("rida");
		assertEquals("rida", dto.getLastName());
	}
	
	@Test 
	void testPassword() {
		String password = "pass";
		dto.setPassword("word");
		assertEquals("word", dto.getPassword());
	}
	
//	@Test
//	public void testCreationDate() {
//		
//	}

}
