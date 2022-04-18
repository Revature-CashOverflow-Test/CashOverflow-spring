package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {

	@Mock
	private UserAccountRepo userRepo;

	private JwtUserDetailsService serv;

	@BeforeEach
	void setUp() throws Exception {
		serv = new JwtUserDetailsService(userRepo);
	}

	@Test
	void loadUserByUsernameTest() {
		UserAccount initial = new UserAccount();
		User expected = new User("tlyer", "pas", new ArrayList<>());
		initial.setUsername("tlyer");
		initial.setPassword("pas");
		
		
		when(userRepo.findByUsername(initial.getUsername())).thenReturn(initial);
		
		UserDetails result = serv.loadUserByUsername(initial.getUsername());
		verify(userRepo, times(1)).findByUsername(initial.getUsername());
		assertEquals(expected, result);
	}
	
	@Test
	void testUsernameNotFound() {
		UserAccount initial = new UserAccount();
		UserAccount expected = new UserAccount();
		initial.setUsername(null);
		initial.setPassword("pas");
		expected.setUsername("tyler");
		expected.setPassword("pas");
		
		when(userRepo.findByUsername(initial.getUsername())).thenReturn(null);
		String usernameForSonar = initial.getUsername();
		assertThrows(UsernameNotFoundException.class, () -> serv.loadUserByUsername(usernameForSonar));
	}
}
