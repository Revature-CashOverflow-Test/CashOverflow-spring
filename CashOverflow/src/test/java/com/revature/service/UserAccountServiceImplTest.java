package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

@SpringBootTest
class UserAccountServiceImplTest {

	@Mock
	private UserAccountRepo userRepo;
	
	private UserAccountService serv;
	
	@BeforeEach
	void setUp() throws Exception {
		serv = new UserAccountServiceImpl(userRepo);
	}

	@Test
	void testGetUserFromUsername() {
		UserAccount initial = new UserAccount();
		UserAccount expected = new UserAccount();
		initial.setUsername("tyler");
		initial.setPassword("pas");
		expected.setUsername("tyler");
		expected.setPassword("pas");
		
		when(userRepo.findByUsername(initial.getUsername())).thenReturn(initial);
		
		UserAccount result = serv.getUserFromUsername(initial.getUsername());
		verify(userRepo, times(1)).findByUsername(initial.getUsername());
		assertEquals(expected, result);
	}
	
	@Test
	void testGetUserFromUsernameFail() {
		UserAccount initial = new UserAccount();
		initial.setUsername("tyler");
		initial.setPassword("pas");
		
		when(userRepo.findByUsername(initial.getUsername())).thenReturn(null);
		
		UserAccount result = serv.getUserFromUsername(initial.getUsername());
		verify(userRepo, times(1)).findByUsername(initial.getUsername());
		assertEquals(null, result);
	}

}
