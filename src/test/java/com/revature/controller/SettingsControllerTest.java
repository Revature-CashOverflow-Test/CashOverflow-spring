package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.controller.SettingsController;
import com.revature.dto.EmailSettingsDto;
import com.revature.dto.SettingsDto;
import com.revature.service.SettingsService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SettingsControllerTest {
	@Mock
	private SettingsService settingsService;
	
	@Mock
	private PasswordEncoder encoder;
	
	@Mock
	private Authentication auth;
	
	private SettingsController controller;
	
	@BeforeEach
	void setUpBeforeClass() {
		controller = new SettingsController(settingsService, encoder);
	}
	
	@Test
	void testChangePassword() {
		SettingsDto settingsDto = new SettingsDto("mbaileyfuturist", "12!@QW44");
		String passHash = "beepbeepboop";
		
		when(encoder.encode(settingsDto.getNewPassword())).thenReturn(passHash);
		when(settingsService.changePassword(settingsDto.getUsername(), passHash)).thenReturn(1);
		
		assertTrue(controller.changePassword(settingsDto));
	}
	
	@Test
	void testChangeEmailSettings() {
		String username = "Protogen";
		
		EmailSettingsDto emailSettingsDto = new EmailSettingsDto(true, 123.37);
		
		when(auth.getName()).thenReturn(username);
		when(settingsService.changeEmailSettings(username, emailSettingsDto.isEmailToggle(), emailSettingsDto.getEmailValue())).thenReturn(true);
		
		assertTrue(controller.changeEmailSettings(auth, emailSettingsDto));
	}
}