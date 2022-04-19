package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

 /**
  * 
  * 
  * Testing getters/setter for the BetweenUserDto
  *
  */

class BetweenUsersDtoTest {
	
	@Mock
	BetweenUsersDto bdto = new BetweenUsersDto();
	
	@Test
	void testId() {
		int id = 2;
		bdto.setId(3);
		assertEquals(3, bdto.getId());
	}
	
	@Test
	void testSendOrReceive() {
		int sendOrReceive = 1;
		bdto.setSendOrReceive(0);
		assertEquals(0, bdto.getSendOrReceive());
	}
	
	@Test
	void testUser() {
		String user = "WeezyFBaby";
		bdto.setUser("Wayne");
		assertEquals("Wayne", bdto.getUser());
	}
	
	@Test
	void testOriginUser() {
		String originUser = "Jim";
		bdto.setOriginUser("Jimmy");
		assertEquals("Jimmy", bdto.getOriginUser());
	}
	
	@Test
	void testReceiveAccount() {
		int receiveAccount = 1;
		bdto.setReceiveAccount(2);
		assertEquals(2, bdto.getReceiveAccount());
	}
	
	@Test
	void testTransferAccount() {
		int transferAccount = 12;
		bdto.setTransferAccount(14);
		assertEquals(14, bdto.getTransferAccount());
	}
	
	@Test
	void testTransferAmount() {
		double transferAmount = 100.00;
		bdto.setTransferAmount(50.00);
		assertEquals(50.00, bdto.getTransferAmount());
	}

}
