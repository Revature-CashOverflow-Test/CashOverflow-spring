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

		SettingsDto settingsDtoTwo = new SettingsDto();
		value = settingsService.changePassword(settingsDtoTwo.getUsername(), settingsDtoTwo.getNewPassword());
		assertEquals(0, value);
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
		SettingsDto dto = new SettingsDto();
		dto.setUsername("mbaileyfuturist");
		dto.setNewFirstName(null);
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(dto.getUsername(), dto.getNewFirstName());
		});
	}

	// Checks to see if a null nuser name is set
	@Test
	void testChangeFirstNameSettingNullUsernameFailure() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewFirstName("Micheal");
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(dto.getUsername(), dto.getNewFirstName());
		});
	}

	// Checks to see if the setting Service changes the logic for a successful last
	// name change
	@Test
	void testChangeLastNameSettingSuccess() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("mbaileyfuturist");
		dto.setNewLastName("Bailey");
		int value = settingsService.changeLastName(dto.getUsername(), dto.getNewLastName());
		assertEquals(1, value);
	}

	// Checks to see if a null new First Name is set
	@Test
	void testChangeLastNameSettingNullFirstNameFailure() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("mbaileyfuturist");
		dto.setNewLastName(null);
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeLastName(dto.getUsername(), dto.getNewLastName());
		});
	}

	// Checks to see if a null user name is set
	@Test
	void testChangeLastNameSettingNullUsernameFailure() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewLastName("Bailey");
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeLastName(dto.getUsername(), dto.getNewLastName());
		});
	}

	// Checks to see if the setting Service changes the logic for a successful
	// email change
	@Test
	void testChangeEmailSettingSuccess() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("mbaileyfuturist");
		dto.setNewEmail("mbaileyfuturist@gmail.com");
		int value = settingsService.changeEmail(dto.getUsername(), dto.getNewEmail());
		assertEquals(1, value);
	}

	// Checks to see if a null email is set
	@Test
	void testChangeEmailSettingNullFirstNameFailure() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("mbaileyfuturist");
		dto.setNewEmail(null);
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(dto.getUsername(), dto.getNewEmail());
		});
	}

	// Checks to see if a null user nameis set
	@Test
	void testChangeEmailSettingNullUsernameFailure() {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewEmail("mbaileyfuturist@gmail.com");
		assertThrows(ResponseStatusException.class, () -> {
			settingsService.changeFirstName(dto.getUsername(), dto.getNewEmail());
		});
	}


}
