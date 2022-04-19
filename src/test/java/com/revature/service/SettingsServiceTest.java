package com.revature.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	void testChangePasswordSuccess() {
		String username = "test";
		String password = "test";
		
		when(repo.changePassword(username, password)).thenReturn(1);
		
		assertEquals(1, serv.changePassword(username, password));
	}
	
	@Test
	void testChangePasswordNoUsername() {
		String username = null;
		String password = "tongue";
		
		assertEquals(0, serv.changePassword(username, password));
	}
	
	@Test
	void testChangePasswordNoPassword() {
		String username = "Glue";
		String password = null;
		
		assertEquals(0, serv.changePassword(username, password));
	}

	@Test
	void testChangeEmailSettingsSuccess() {
		String username = "test";
		boolean emailToggle = true;
		double emailValue = 50.0;
		
		when(repo.changeEmailSettings(username, emailToggle, emailValue)).thenReturn(1);
		
		assertTrue(serv.changeEmailSettings(username, emailToggle, emailValue));
	}
	
	@Test
	void testChangeEmailSettingsFailure() {
		String username = "Glue";
		boolean emailToggle = false;
		double emailValue = 420.00;
		
		when(repo.changeEmailSettings(username, emailToggle, emailValue)).thenReturn(0);
		
		assertFalse(serv.changeEmailSettings(username, emailToggle, emailValue));
	}
}
