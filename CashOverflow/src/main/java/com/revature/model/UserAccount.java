package com.revature.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Model for user accounts
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String email;
	String username;
	String firstName;
	String lastName;
	String password;
	Instant creationDate;
	
	public UserAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
}
