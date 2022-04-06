package com.revature.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.BankAccountRepo;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.model.FundTransfer;
import com.revature.model.UserAccount;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class AccountControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private BankAccountRepo bankRepo;
	
	@Autowired
	private UserAccountRepo userRepo;
	
	@Autowired
	private PasswordEncoder enc;
	
	@BeforeEach
	void setUp() {
		bankRepo.deleteAll();
		userRepo.deleteAll();
		
		UserAccount user = new UserAccount(0, "user1@gmail.com", "user1", "user", "1", enc.encode("user1"), Instant.now());
		UserAccount user2 = new UserAccount(0, "user2@gmail.com", "user2", "user", "2", enc.encode("user2"), Instant.now());
		userRepo.save(user);
		userRepo.save(user2);
		user = userRepo.findByUsername("user1");
		user2 = userRepo.findByUsername("user2");
		
		bankRepo.save(new BankAccount(0, "name1", 100.0, "description1", Instant.now(), 1, user, null));
		bankRepo.save(new BankAccount(0, "name2", 0.0, "description2", Instant.now(), 1, user, null));
		bankRepo.save(new BankAccount(0, "name3", 0.0, "description3", Instant.now(), 2, user, null));
		bankRepo.save(new BankAccount(0, "name1u2", 100.0, "description1u2", Instant.now(), 2, user2, null));
	}
	
	@ParameterizedTest
	@WithMockUser("user1")
	@NullSource
	@ValueSource(strings = { "", "name1" })
	void testCreateAccountName(String arg) throws Exception {
		BankAccountDto dto = new BankAccountDto();
		dto.setDescription("description");
		dto.setName(arg);
		dto.setAccountTypeId(1);
		
		mvc.perform(post("/api/account/createBankAccount").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser("user1")
	void testCreateAccountSuccess() throws Exception {
		BankAccountDto dto = new BankAccountDto();
		dto.setDescription("description");
		dto.setName("name");
		dto.setAccountTypeId(1);
		
		MvcResult result = mvc.perform(post("/api/account/createBankAccount").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		BankAccountDto actualDto = mapper.readValue(responseBody, BankAccountDto.class);
		BankAccountDto expectedDto = new BankAccountDto();
		expectedDto.setId(actualDto.getId());
		expectedDto.setBalance(0.0);
		expectedDto.setName("name");
		expectedDto.setAccountTypeId(1);
		expectedDto.setCreationDate(actualDto.getCreationDate());
		expectedDto.setDescription("description");
		
		assertEquals(expectedDto, actualDto);
	}
	
	@Test
	@WithMockUser("user1")
	void testGetBankAccounts() throws Exception {
		MvcResult result = mvc.perform(get("/api/account/getBankAccounts"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(authenticated().withAuthenticationName("user1"))
			.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		List<BankAccountDto> dtos = mapper.readValue(responseBody, new TypeReference<List<BankAccountDto>>() {});
		
		assertEquals(3, dtos.size());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNull() throws Exception {
		FundTransfer ft = new FundTransfer(null, "name2", 1.0);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNotEnough() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "name2", 100.01);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNotYourBankAccount() throws Exception {
		FundTransfer ft = new FundTransfer("name1u2", "name2", 50.0);

		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNonexistentFromAccount() throws Exception {
		FundTransfer ft = new FundTransfer("xddd", "name2", 50.0);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNonexistentToAccount() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "xddd", 50.0);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferSuccess() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "name2", 99.0);
		
		MvcResult result = mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(authenticated().withAuthenticationName("user1"))
			.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		List<BankAccountDto> actualAccts = mapper.readValue(responseBody, new TypeReference<List<BankAccountDto>>() {});
		
		assertEquals(2, actualAccts.size());
		assertEquals(1.0, actualAccts.get(0).getBalance());
		assertEquals(99.0, actualAccts.get(1).getBalance());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNegativeAmount() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "name2", -50.0);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsZeroAmount() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "name2", 0.0);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsNullAmount() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "name2", null);
		
		mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isIAmATeapot());
	}
	
	@Test
	@WithMockUser("user1")
	void testTransferFundsFractionalPennies() throws Exception {
		FundTransfer ft = new FundTransfer("name1", "name2", 50.006);
		
		MvcResult result = mvc.perform(post("/api/account/transferFunds").content(mapper.writeValueAsString(ft)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(authenticated().withAuthenticationName("user1"))
			.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		List<BankAccountDto> actualAccts = mapper.readValue(responseBody, new TypeReference<List<BankAccountDto>>() {});
		
		assertEquals(2, actualAccts.size());
		assertEquals(100.0, actualAccts.get(0).getBalance() + actualAccts.get(1).getBalance());
		assertEquals(49.99, actualAccts.get(0).getBalance());
		assertEquals(50.01, actualAccts.get(1).getBalance());
	}
	
	
}
