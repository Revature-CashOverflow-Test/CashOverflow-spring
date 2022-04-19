package com.revature.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dao.SettingsRepo;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SettingsServiceTest {
	
	@Mock
	private SettingsRepo repo;
	
	private SettingsService serv;
	
	@BeforeEach
	void setUp() {
		serv = new SettingsService(repo);
	}
	
	@Test
	void testChangePassword() {
		String username = "test";
		String password = "test";
		
		when(repo.changePassword(username, password)).thenReturn(1);
		
		assertEquals(1, serv.changePassword(username, password));
	}

	@Test
	void testChangeEmailSettings() {
		String username = "test";
		boolean emailToggle = true;
		double emailValue = 50.0;
		
		when(repo.changeEmailSettings(username, emailToggle, emailValue)).thenReturn(1);
		
		assertTrue(serv.changeEmailSettings(username, emailToggle, emailValue));
	}
}
