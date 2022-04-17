package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class BetweenUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer sendOrReceive;
	String user;
	String originUser;
	Integer receiveAccount;
	Integer transferAccount;
	Double transferAmount;
	
	public BetweenUsers(Integer sendOrReceive, String user, Integer transferAccount, Double transferAmount) {
		super();
		this.sendOrReceive = sendOrReceive;
		this.user = user;
		this.transferAccount = transferAccount;
		this.transferAmount = transferAmount;
	}
	
	
}
