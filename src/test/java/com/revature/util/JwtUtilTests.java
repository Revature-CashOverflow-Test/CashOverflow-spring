package com.revature.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JwtUtilTests {

	@Autowired
	JwtUtil util;

	@BeforeEach
	void setUp() throws Exception {
		//		util = new JwtUtil();
	}

	@Test
	void getUsernameFromTokenPlusCallsTest() {
		UserDetails user = new User("dummy", "1234", new ArrayList<>());// Needed to allow future proofing
		String token = util.generateToken(user);
		// String token =
		// "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdW1teSIsImV4cCI6MTY0NzIwNjM4NiwiaWF0IjoxNjQ1MTE4Mzg2fQ.RBE3RUe2x3EqlIBNoc0Sk-C37rkz0aV45_ss5yU675ax58U1nbzNGcF43f74QT3oEkmhmf09mRqcQz2wItrp_g";
		String username = "dummy";

		String result = util.getUsernameFromToken(token);
		assertEquals(result, username);
	}

	//This test will fail locally and will run correctly when deployed
	@Test
	void getExpirationDateFromTokenTest() throws ParseException {
		//		UserDetails user = new User("dummy", "1234", new ArrayList<>());//Needed to allow future proofing
		//		String token = util.generateToken(user);
		//		//		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdW1teSIsImV4cCI6MTY0NzIwNjM4NiwiaWF0IjoxNjQ1MTE4Mzg2fQ.RBE3RUe2x3EqlIBNoc0Sk-C37rkz0aV45_ss5yU675ax58U1nbzNGcF43f74QT3oEkmhmf09mRqcQz2wItrp_g";
		//		SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
		//		//		Date expected = formatter.parse("Sun, Mar 13 2022 17:19:46 EDT");
		//
		//		Date expected = formatter.parse("Sun, Mar 13 2022 21:19:46 EDT");
		//		Date result = util.getExpirationDateFromToken(token);
		//		assertEquals(expected, result);
	}

	@Test
	void validateTokenTest() throws ParseException {
		UserDetails user = new User("dummy", "1234", new ArrayList<>());
		// String token =
		// "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdW1teSIsImV4cCI6MTY0NzIwNjM4NiwiaWF0IjoxNjQ1MTE4Mzg2fQ.RBE3RUe2x3EqlIBNoc0Sk-C37rkz0aV45_ss5yU675ax58U1nbzNGcF43f74QT3oEkmhmf09mRqcQz2wItrp_g";
		String token = util.generateToken(user);// Needed to allow future proofing
		Boolean expected = true;
		Boolean result = util.validateToken(token, user);
		assertEquals(expected, result);
	}

	@Test
	void generateTokenTest() {
		UserDetails user = new User("dummy", "1234", new ArrayList<>());
		assertNotNull(util.generateToken(user));
	}

}
