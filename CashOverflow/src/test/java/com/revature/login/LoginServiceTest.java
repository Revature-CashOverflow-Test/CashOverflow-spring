package com.revature.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;
import com.revature.service.LoginService;
import com.revature.service.LoginServiceImpl;

/**
 * this is a test to check if the LoginController.login() method work as it
 * should
 * 
 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	@Mock
	private UserAccountRepo repo;
	
	private LoginService serv;
	
	private UserAccount initial, expected;

	@BeforeEach
	void setUp() throws Exception {
		serv = new LoginServiceImpl(repo);
		initial = new UserAccount();
		initial.setUsername("dummy");
		initial.setPassword("password");
		expected = new UserAccount();
		expected.setUsername("dummy");
		expected.setPassword("password");
	}

	@Test
	void login() {
		when(repo.findByUsernameAndPassword("dummy", "password")).thenReturn(initial);

		UserAccount works = serv.login("dummy", "password");
		assertEquals(works, expected);

	};

}
