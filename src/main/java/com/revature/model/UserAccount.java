package com.revature.model;

import java.time.Instant;

import javax.persistence.Column;
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
	//	@SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE", allocationSize = 1)
	//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Perhaps this will fix the id is null?
	//	@Column(name = "id", updatable = false, nullable = false)
	Integer id;

	String email;

	@Column(unique = true)
	String username;
	String firstName;
	String lastName;
	String password;
	Instant creationDate;
	boolean authAccount = false;
	Boolean emailToggle = false;
	Double emailValue = 0.0;
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserAccount(Integer id, String email, String username, String firstName, String lastName, String password,
			Instant creationDate) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.creationDate = creationDate;
	}



}
