package com.revature.dto;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.revature.model.UserAccount;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for user accounts to send to the client
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Data
@NoArgsConstructor
public class UserAccountDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String email;
	String username;
	String firstName;
	String lastName;
	//String password;
	Instant creationDate;
	Boolean emailToggle;
	Double emailValue;

	public UserAccountDto(UserAccount user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		//this.password = user.getPassword();
		this.email = user.getEmail();
		this.creationDate = user.getCreationDate();
		this.emailToggle = user.getEmailToggle();
		this.emailValue = user.getEmailValue();
	}
}