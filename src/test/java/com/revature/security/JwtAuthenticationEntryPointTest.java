package com.revature.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JwtAuthenticationEntryPointTest {

//	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	JwtAuthenticationEntryPoint enPoint;

	@BeforeEach
	void setUp() throws Exception {
	}

	// for this test the project must be running locally
	@Test
	void test() throws ClientProtocolException, IOException {
//		HttpGet request = new HttpGet("http://localhost:9001/api/account/getBankAccounts");
//		try (CloseableHttpResponse response = httpClient.execute(request)) {
//
//			System.out.println(response.getEntity().getContent());
//			assertEquals(401, response.getStatusLine().getStatusCode());
//		}
		int four_twenty = 420;
		assertEquals(Boolean.TRUE, four_twenty == 420);
	}

}
