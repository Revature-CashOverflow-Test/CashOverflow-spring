package com.revature.dto;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.revature.model.UserAccount;

import lombok.Data;

/**
 * Model for user accounts to send to the client
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Data
public class UserAccountDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String username;
	String firstName;
	String lastName;
	String password;
	Instant creationDate;

	public UserAccountDto(UserAccount user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.creationDate = user.getCreationDate();
	}
}