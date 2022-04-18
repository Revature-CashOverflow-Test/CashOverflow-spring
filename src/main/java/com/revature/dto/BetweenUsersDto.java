package com.revature.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetweenUsersDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer sendOrReceive;
	String user;
	String originUser;
	Integer receiveAccount;
	Integer transferAccount;
	Double transferAmount;
	
	public BetweenUsersDto(Integer sendOrReceive, String user, Integer transferAccount, Double transferAmount) {
		super();
		this.sendOrReceive = sendOrReceive;
		this.user = user;
		this.transferAccount = transferAccount;
		this.transferAmount = transferAmount;
	}
}
