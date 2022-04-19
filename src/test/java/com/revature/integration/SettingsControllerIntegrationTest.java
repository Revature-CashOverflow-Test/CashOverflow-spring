package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.SocialAccountRepo;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.SettingsDto;
import com.revature.model.UserAccount;
import com.revature.service.SocialAccountService;

/**
 * Integration tests for the login controller
 *
 * @author Caleb Kirschbaum
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class SettingsControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UserAccountRepo repo;

	@Autowired
	private SocialAccountRepo socialRepo;

	@Autowired
	private PasswordEncoder enc;

	@Mock
	SocialAccountService saserv;

	private Instant currentTime = Instant.now();

	@BeforeEach
	void setUpEach() {
		repo.deleteAll();
		socialRepo.deleteAll();
		repo.save(new UserAccount(1, "user1@gmail.com", "user1", "user", "1", enc.encode("user1"), Instant.now()));
		repo.save(new UserAccount(2, "user2@gmail.com", "user2", "user", "2", enc.encode("user2"), Instant.now()));
		repo.save(new UserAccount(3, "user3@gmail.com", "user3", "user", "3", enc.encode("user3"), Instant.now()));
		UserAccount bob = new UserAccount(4, "user4@gmail.com", "user4", "user", "4", enc.encode("auth0User"),
				currentTime);
		repo.save(bob);
	}

	@Test
	void testChangePasswordWrongMethod() throws Exception {
		mvc.perform(get("/changePassword")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testChangePasswordMissingUserName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewPassword("NewUser");
		mvc.perform(
				put("/changePassword").content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testChangePasswordMissingPassword() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewPassword(null);
		mvc.perform(
				put("/changePassword").content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testChangePasswordUserNotFound() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("username");
		dto.setNewPassword("NewUser");
		MvcResult result = mvc
				.perform(put("/changePassword").content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();
		assert ("false".equals(responseBody));
	}

	@Test
	void testChangePasswordSuccess() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewPassword("NewUser");
		MvcResult result = mvc
				.perform(put("/changePassword").content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();
		assert ("true".equals(responseBody));
	}

	@Test
	void testSetNewFirstNameWrongMethod() throws Exception {
		mvc.perform(get("/changeFirstName")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testSetNewFirstNameMissingUserName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewFirstName("NewUser");
		mvc.perform(put("/changeFirstName").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	@Test
	void testSetNewFirstNameMissingFirstName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewFirstName(null);
		mvc.perform(put("/changeFirstName").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	@Test
	void testNewFirstNameUserNotFound() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("username");
		dto.setNewFirstName("NewUser");
		MvcResult result = mvc.perform(
				put("/changeFirstName").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();


		String responseBody = result.getResponse().getContentAsString();
		assert ("false".equals(responseBody));
	}

	@Test
	void testSetNewFirstNameSuccess() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewFirstName("NewUser");
		MvcResult result = mvc
				.perform(put("/changeFirstName")
						.content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();
		assert ("true".equals(responseBody));
	}

	@Test
	void testSetNewLastNameWrongMethod() throws Exception {
		mvc.perform(get("/changeLastName")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testSetNewLastNameMissingUserName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewLastName("NewUser");
		mvc.perform(
				put("/changeLastName").content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testSetNewLastNameMissingFirstName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewLastName(null);
		mvc.perform(
				put("/changeLastName").content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testNewLastNameUserNotFound() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("username");
		dto.setNewLastName("NewUser");
		MvcResult result = mvc
				.perform(put("/changeLastName").content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		String responseBody = result.getResponse().getContentAsString();
		assert ("false".equals(responseBody));
	}

	@Test
	void testSetNewLastNameSuccess() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewLastName("NewUser");
		MvcResult result = mvc
				.perform(put("/changeLastName").content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		String responseBody = result.getResponse().getContentAsString();
		assert ("true".equals(responseBody));
	}


	@Test
	void testSetNewEmailWrongMethod() throws Exception {
		mvc.perform(get("/changeEmail")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testSetNewEmailMissingUserName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername(null);
		dto.setNewEmail("NewUser");
		mvc.perform(put("/changeEmail").content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testSetNewEmailMissingFirstName() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewEmail(null);
		mvc.perform(put("/changeEmail").content(mapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testNewEmailUserNotFound() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("username");
		dto.setNewEmail("NewUser");
		MvcResult result = mvc
				.perform(put("/changeEmail").content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		String responseBody = result.getResponse().getContentAsString();
		assert ("false".equals(responseBody));
	}

	@Test
	void testSetEmailNameSuccess() throws Exception {
		SettingsDto dto = new SettingsDto();
		dto.setUsername("user1");
		dto.setNewEmail("NewUser");
		MvcResult result = mvc
				.perform(put("/changeEmail").content(mapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		String responseBody = result.getResponse().getContentAsString();
		assert ("true".equals(responseBody));
	}

}
