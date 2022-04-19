package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class SettingsDtoTest {
	
	@Mock 
	SettingsDto dto = new SettingsDto();
	
	@Test
	void testUsername() {
		String username = "jimjohn";
		dto.setUsername("JimmyJohn");
		assertEquals("JimmyJohn", dto.getUsername());
	}
	
	@Test
	 void testNewPassword() {
		String newPassword = "newpassword";
		dto.setNewPassword("badpassword");
		assertEquals("badpassword", dto.getNewPassword());
	}
	

}
