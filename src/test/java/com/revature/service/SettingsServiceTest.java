package com.revature.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.UserAccountRepo;
import com.revature.dto.SettingsDto;
import com.revature.model.UserAccount;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SettingsServiceTest {

	@Autowired
	private SettingsService settingsService;

	@Autowired
	private UserAccountRepo repo;
	@Autowired
	private PasswordEncoder enc;

	@BeforeEach
	void setUpEach() {
		repo.deleteAll();
		repo.save(new UserAccount(3, "mbaileyfuturist@gmail.com", "mbaileyfuturist", "micheal", "lastname",
				enc.encode("12!@QW44"), Instant.now()));

	}

	@Test
	void testSettingsService() {
		SettingsDto settingsDto = new SettingsDto("mbaileyfuturist", "12!@QW44");
		int value = settingsService.changePassword(settingsDto.getUsername(), settingsDto.getNewPassword());
		assertEquals(1, value);

		settingsDto.setUsername("Spiderman");
		settingsDto.setNewPassword("11!!QQww");
		value = settingsService.changePassword(settingsDto.getUsername(), settingsDto.getNewPassword());
		assertEquals(0, value);

		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName("mbaileyfuturist", null);
		});
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(null, "Micheal");
		});
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(null, null);
		});
	}

	// Checks to see if the setting Service changes the logic for a successful first
	// name change
	@Test
	void testChangeFirstNameSettingSuccess() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("mbaileyfuturist");
		dto.setNewFirstName("Micheal");
		int value = settingsService.changeFirstName(dto.getUsername(), dto.getNewFirstName());
		assertEquals(1, value);
	}

	// Checks to see if a null new First Name is set
	@Test
	void testChangeFirstNameSettingNullFirstNameFailure() {
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName("mbaileyfuturist", null);
		});
	}

	// Checks to see if a null nuser name is set
	@Test
	void testChangeFirstNameSettingNullUsernameFailure() {
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(null, "Micheal");
		});
	}

	// Checks to see if the setting Service changes the logic for a successful last
	// name change
	@Test
	void testChangeLastNameSettingSuccess() {
		int value = settingsService.changeLastName("mbaileyfuturist", "Bailey");
		assertEquals(1, value);
	}

	// Checks to see if a null new First Name is set
	@Test
	void testChangeLastNameSettingNullFirstNameFailure() {
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeLastName("mbaileyfuturist", null);
		});
	}

	// Checks to see if a null user name is set
	@Test
	void testChangeLastNameSettingNullUsernameFailure() {
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeLastName(null, "Bailey");
		});
	}

	// Checks to see if the setting Service changes the logic for a successful
	// email change
	@Test
	void testChangeEmailSettingSuccess() {
		int value = settingsService.changeEmail("mbaileyfuturist", "mbaileyfuturist@gmail.com");
		assertEquals(1, value);
	}

	// Checks to see if a null email is set
	@Test
	void testChangeEmailSettingNullFirstNameFailure() {
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName("mbaileyfuturist", null);
		});
	}

	// Checks to see if a null user nameis set
	@Test
	void testChangeEmailSettingNullUsernameFailure() {
		SettingsDto dto = new SettingsDto();
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(null, "mbaileyfuturist@gmail.com");
		});
	}


}
