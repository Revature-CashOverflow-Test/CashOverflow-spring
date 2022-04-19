package com.revature.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.revature.controller.LoginController;
import com.revature.dto.LoginRequestDto;
import com.revature.model.UserAccount;
import com.revature.service.JwtAuthenticationService;
import com.revature.service.SocialAccountService;
import com.revature.service.UserAccountService;

/**
 *
 * Tests login controller functionality.
 *
 * @author Delane Chen, Liliya Sherstobitova, Emmanuel Sosa
 *
 */

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

	LoginController loginController;

	@Mock
	UserAccountService serv;

	@Mock
	JwtAuthenticationService jwtServ;

	@Mock
	PasswordEncoder enc;

	@Mock
	SocialAccountService saserv;

	@BeforeEach
	void setup() {
		loginController = new LoginController(serv, jwtServ, enc, saserv);
	}

	//Null credential failure
	@Test
	void loginFailTest() {
		LoginRequestDto req = new LoginRequestDto(null, null);
		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			loginController.login(req);
		});

		String expectedReason = "missing Credential";
		HttpStatus expectedStatus = HttpStatus.NOT_ACCEPTABLE;
		Integer expectedCode = 406;

		assertEquals(expectedReason, e.getReason());
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());
	}

	//Bad credential failure
	@Test
	void loginFailureTest() {
		LoginRequestDto req = new LoginRequestDto("dummy", "padsojgfhldsoajord");
		UserAccount initial = new UserAccount("dummy", enc.encode("password"));

		when(serv.getUserFromUsername("dummy")).thenReturn(initial);
		assertThrows(ResponseStatusException.class, () -> loginController.login(req));
	}

	// Test if an Auth0 User is logging in normally
	// If they are, block the login
	@Test
	void loginAuth0LogsInNormalFailureTest() {
		// Expected values
		String expectedReason = "Auth0 User can't sign in using the normal Login";
		HttpStatus expectedStatus = HttpStatus.NOT_ACCEPTABLE;
		Integer expectedCode = 406;

		// Users to test
		LoginRequestDto req = new LoginRequestDto("dummy", "password");
		UserAccount initial = new UserAccount("dummy", enc.encode("password"));
		initial.setAuthAccount(true);

		// Return the initial when getting user req
		when(serv.getUserFromUsername("dummy")).thenReturn(initial);
		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			loginController.login(req);
		});

		// SHould throw these errors
		assertEquals(expectedReason, e.getReason());
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());
	}

	// Test if an auth0 user signs in without a user name somehow
	@Test
	void loginAuth0LogsInWithNullUsernameFailureTest() {
		// Expected values
		String expectedReason = "missing Credential";
		HttpStatus expectedStatus = HttpStatus.NOT_ACCEPTABLE;
		Integer expectedCode = 406;

		// Users to test
		LoginRequestDto req = new LoginRequestDto(null, "password", true);
		// UserAccount initial = new UserAccount("dummy", enc.encode("password"));
		// initial.setAuthAccount(true);

		// Return the initial when getting user req
		// when(serv.getUserFromUsername("dummy")).thenReturn(initial);
		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			loginController.login(req);
		});

		// SHould throw these errors
		assertEquals(expectedReason, e.getReason());
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());
	}

	// Test if an auth0 user signs in when no user exists
	@Test
	void loginAuth0LogInWithoutValidUserFailureTest() {
		// Expected values
		String expectedReason = "User is not found";
		HttpStatus expectedStatus = HttpStatus.NOT_ACCEPTABLE;
		Integer expectedCode = 406;

		// Users to test
		LoginRequestDto req = new LoginRequestDto("dummy", "password", true);
		//			 UserAccount initial = new UserAccount("dummy", enc.encode("password"));
		// initial.setAuthAccount(true);

		// Return the initial when getting user req
		when(serv.getUserFromUsername("dummy")).thenReturn(null);
		when(saserv.getSocialAccount("dummy")).thenReturn(null);
		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			loginController.login(req);
		});

		// SHould throw these errors
		assertEquals(expectedReason, e.getReason());
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());
	}

}
