package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dao.SocialAccountRepo;
import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;
import com.revature.model.UserSocialMedia;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SocialAccountServiceImplTest {


	@Autowired
	private SocialAccountService serv;

	@Autowired
	private SocialAccountRepo repo;

	@Autowired
	private UserAccountRepo uaRepo;


	@BeforeEach
	void setUpEach() {

		UserAccount person1 = new UserAccount();
		uaRepo.save(person1);
		repo.save(new UserSocialMedia(1, "user1", "SubSomething", person1));
		repo.save(new UserSocialMedia(2, "user2", "SubOther", person1));

	}

	@Test
	void createSocialTestSuccessful() {
		UserAccount person1 = new UserAccount();
		uaRepo.save(person1);
		UserSocialMedia social1 = new UserSocialMedia(3, "testSocialUsername", "SubSomething", person1);
		UserSocialMedia returnedSocial = serv.createSocial(social1);
		assertEquals(social1, returnedSocial);
	}

	@Test
	void createSocialTestUsnameIsNullFail() {
		UserAccount person1 = new UserAccount();
		uaRepo.save(person1);
		
		UserSocialMedia social1 = new UserSocialMedia(3, null, "SubSomething", person1);
		assertThrows(ResponseStatusException.class, () -> serv.createSocial(social1));
		
		UserSocialMedia social2 = new UserSocialMedia(3, "", "SubSomething", person1);
		assertThrows(ResponseStatusException.class, () -> serv.createSocial(social2));
		
		UserSocialMedia social3 = new UserSocialMedia(3, "user1", "SubSomething", person1);
		assertThrows(ResponseStatusException.class, () -> serv.createSocial(social3));
	}
}
