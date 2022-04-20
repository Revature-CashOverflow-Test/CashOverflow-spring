package com.revature.integration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.HashMap;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.SocialAccountRepo;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.LoginRequestDto;
import com.revature.model.UserAccount;
import com.revature.model.UserSocialMedia;
import com.revature.service.SocialAccountService;

/**
 * Integration tests for the login controller
 *
 * @author Colin Knox
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class LoginControllerIntegrationTest {

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

	private Instant currentTime = Instant.parse("2007-12-03T10:15:30.00Z");

	@BeforeEach
	void setUpEach() {
		repo.deleteAll();
		socialRepo.deleteAll();
		repo.save(new UserAccount(1, "user1@gmail.com", "user1", "user", "1", enc.encode("user1"), Instant.now()));
		repo.save(new UserAccount(2, "user2@gmail.com", "user2", "user", "2", enc.encode("user2"), Instant.now()));
		repo.save(new UserAccount(3, "user3@gmail.com", "user3", "user", "3", enc.encode("user3"), Instant.now()));
		UserAccount AuthTestUser = new UserAccount(4, "user4@gmail.com", "user4", "user", "4", enc.encode("auth0User"),
				currentTime);

		AuthTestUser.setAuthAccount(true);
		repo.save(AuthTestUser);




	}

	@Test
	void testGetWrongMethod() throws Exception {
		mvc.perform(get("/login")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testPostMissingCredentials() throws Exception {
		mvc.perform(post("/login").content(mapper.writeValueAsString(new LoginRequestDto("username", null)))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	@Test
	void testLoginUserNotFound() throws Exception {
		mvc.perform(post("/login").content(mapper.writeValueAsString(new LoginRequestDto("username", "user1")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	@Test
	void testLoginUserSuccess() throws Exception {
		MvcResult result = mvc
				.perform(post("/login").content(mapper.writeValueAsString(new LoginRequestDto("user1", "user1")))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();
		HashMap<String, Object> pairs = mapper.readValue(responseBody, new TypeReference<HashMap<String, Object>>(){});
		assertTrue(pairs.containsKey("jwt"));
		assertNotEquals("", pairs.get("jwt"));
	}

	// Test Auth0 Login is successful
	@Test
	void testAuth0LoginUserSuccess() throws Exception {
		MvcResult result = mvc
				.perform(post("/login")
						.content(mapper.writeValueAsString(new LoginRequestDto("user4", "auth0User", true)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();
		HashMap<String, Object> pairs = mapper.readValue(responseBody, new TypeReference<HashMap<String, Object>>() {
		});
		assertTrue(pairs.containsKey("jwt"));
		assertNotEquals("", pairs.get("jwt"));
	}

	// Makes sure the password the front passes doesn't influence the login
	@Test
	void testAuth0LoginUserSuccessIgnoresPassword() throws Exception {
		MvcResult result = mvc
				.perform(post("/login").content(mapper.writeValueAsString(new LoginRequestDto("user4", " ", true)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();
		HashMap<String, Object> pairs = mapper.readValue(responseBody, new TypeReference<HashMap<String, Object>>() {
		});
		assertTrue(pairs.containsKey("jwt"));
		assertNotEquals("", pairs.get("jwt"));
	}
}