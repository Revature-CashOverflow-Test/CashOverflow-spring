package com.revature.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.RegUserAccountDto;
import com.revature.model.UserAccount;

/**
 * Integration tests for the register controller
 * 
 * @author Colin Knox
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class RegisterControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UserAccountRepo repo;

	@Autowired
	private PasswordEncoder enc;

	@BeforeEach
	void setUpEach() {
		repo.deleteAll();
	}

	@Test
	void testWrongMethod() throws Exception {
		mvc.perform(get("/register")).andExpect(status().isMethodNotAllowed());
		
		mvc.perform(put("/register")).andExpect(status().isMethodNotAllowed());
		
		mvc.perform(delete("/register")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testNullInputValue() throws Exception {
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto(null, "username", "first", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", null, "first", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", null, "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", "first", null, "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", "first", "last", null)))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	void testBlankInputValue() throws Exception {
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("", "username", "first", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "", "first", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", "", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", "first", "", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", "first", "last", "")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	void testInvalidEmailString() throws Exception {
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email", "username", "first", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	void testRegisterSuccess() throws Exception {
		mvc.perform(post("/register").content(mapper.writeValueAsString(new RegUserAccountDto("email@gmail.com", "username", "first", "last", "password")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		UserAccount actualUser = repo.findByUsername("username");
		UserAccount expectedUser = new UserAccount(actualUser.getId(), "email@gmail.com", "username", "first", "last", actualUser.getPassword(), actualUser.getCreationDate());
		
		assertTrue(enc.matches("password", actualUser.getPassword()));
		assertNotNull(actualUser.getCreationDate());
		assertEquals(expectedUser, actualUser);
	}
}
