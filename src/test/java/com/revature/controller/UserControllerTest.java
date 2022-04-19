package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import com.revature.dto.UserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@Mock
	private UserAccountService userAccServ;
	@Mock
	Authentication auth;
	@Mock
	private ModelMapper mapper;

	private UserController userController;

	@BeforeEach
	void setUpBeforeClass() {
		userController = new UserController(userAccServ, mapper);
	}

	@Test
	void testGetUserInfo() {
		UserAccount user = new UserAccount(1, "merp@merp.com", "sergal", "Rain", "Silves", "morp???", null, false,
				false, 0.0);
		UserAccountDto userDto = new UserAccountDto(user);

		when(auth.getName()).thenReturn("sergal");
		when(userAccServ.getUserFromUsername(anyString())).thenReturn(user);
		when(mapper.map(isA(UserAccount.class), isA(Class.class))).thenReturn(userDto);

		assertEquals(userDto, userController.getUserInfo(auth));
	}
}
