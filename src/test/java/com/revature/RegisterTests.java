package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.revature.controller.RegisterController;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.RegUserAccountDto;
import com.revature.dto.SocialAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.BankAccountService;
import com.revature.service.RegisterService;
import com.revature.service.RegisterServiceImpl;
import com.revature.service.SocialAccountService;
import com.revature.service.UserAccountService;

/**
 * Tests for RegisterController(positive and negative) and Service layer. In the
 * future we will put tests for different classes in different testing files.
 *
 * @author Cameron, Amir, Chandra
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RegisterTests {

	private RegisterServiceImpl regServ;
	private RegisterController regCont;


	@Mock
	private UserAccountService serv;

	@Mock
	private SocialAccountService userSocialServ;

	@Mock
	private BankAccountService bankAccServ;

	@Mock
	private UserAccountRepo mockRepo;

	@Mock
	private RegisterService mockServ;

	@Mock
	private ModelMapper mockMapper;

	@Mock
	private PasswordEncoder enc;


	@BeforeEach
	public void setUp() {
		regServ = new RegisterServiceImpl(mockRepo);
		regCont = new RegisterController(mockServ, mockMapper, enc, serv, userSocialServ, bankAccServ);
	}

	@Test
	void serviceTest() {
		UserAccount test = new UserAccount(3, "email@gmail.com", "username", "firstname", "lastname", "password", null);
		when(mockRepo.save(test)).thenReturn(test);
		UserAccount result = regServ.insertUserAccount(test);
		verify(mockRepo, times(1)).save(test);
		assertNotNull(result.getCreationDate());
	}

	@Test
	void RegisterControllerTest() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", "firstname", "lastname",
				"password");
		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());
		when(mockMapper.map(test, UserAccount.class)).thenReturn(user);
		regCont.newUser(test);

		verify(mockServ, times(1)).insertUserAccount(user);
		verify(mockMapper, times(1)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingEmail() {
		RegUserAccountDto test = new RegUserAccountDto(null, "username", "firstname", "lastname", "password");

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingUsername() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", null, "firstname", "lastname", "password");

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingFirstName() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", null, "lastname", "password");

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingLastName() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", "firstname", null, "password");

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingPassword() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", "firstname", "lastname", null);

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingEmailAuth0() {
		RegUserAccountDto test = new RegUserAccountDto(null, "username", "firstname", "lastname", "password", true);

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingUsernameAuth0() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", null, "firstname", "lastname", "password",
				true);

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingFirstNameAuth0() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", null, "lastname", "password",
				true);

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingLastNameAuth0() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", "firstname", null, "password",
				true);

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			regCont.newUser(test);
		});

		String expectedReason = "Missing registration info";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());

		verify(mockServ, times(0)).insertUserAccount(user);
		verify(mockMapper, times(0)).map(test, UserAccount.class);

	}

	@Test
	void RegisterControllerTestMissingPasswordAuth0() {
		RegUserAccountDto test = new RegUserAccountDto("email@gmail.com", "username", "firstname", "lastname", null,
				true);

		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());

		when(mockMapper.map(test, UserAccount.class)).thenReturn(user);
		regCont.newUser(test);

		verify(mockServ, times(1)).insertUserAccount(user);
		verify(mockMapper, times(1)).map(test, UserAccount.class);

	}

	@Test
	void addSocial() {
		SocialAccountDto socialAccDto = Mockito.mock(SocialAccountDto.class);
		SocialAccountService socialAccServ = Mockito.mock(SocialAccountService.class);
		Authentication auth = Mockito.mock(Authentication.class);
		UserAccount userAcc = Mockito.mock(UserAccount.class);
		
		socialAccDto.setOwner(userAcc);
		
		
	}
}
