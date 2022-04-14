package com.revature.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dto.SettingsDto;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SettingsServiceTest {
	
	@Mock
	private SettingsService settingsService;
	
	@Test
	public void testSettingsService() {
		
		SettingsDto settingsDto = new SettingsDto("mbaileyfuturist", "12!@QW44");
		
		when(settingsService.changePassword(settingsDto.getUsername(), settingsDto.getNewPassword())).thenReturn(1);
		
		int value = settingsService.changePassword(settingsDto.getUsername(), settingsDto.getNewPassword());
		
		assertEquals(1, value);
		
		settingsDto.setUsername("Spiderman");
		settingsDto.setNewPassword("11!!QQww");
		
		when(settingsService.changePassword(settingsDto.getUsername(), settingsDto.getNewPassword())).thenReturn(1);
		
		value = settingsService.changePassword(settingsDto.getUsername(), settingsDto.getNewPassword());
		
		assertEquals(1, value);
		
		SettingsDto settingsDtoTwo = new SettingsDto();
		
		when(settingsService.changePassword(settingsDtoTwo.getUsername(), settingsDtoTwo.getNewPassword())).thenReturn(0);

		value = settingsService.changePassword(settingsDtoTwo.getUsername(), settingsDtoTwo.getNewPassword());

		assertEquals(0, value);

		


	}

}
