package com.revature.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for bank accounts
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Entity
@Table(name = "bank_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	Double balance;
	String description;
	Instant creationDate;
	Integer accountTypeId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	UserAccount user;
	
	@OneToMany
	@JsonIgnore
	@JoinColumn(referencedColumnName = "id")
	List<Transaction> txs;
	

	public BankAccount(String name, String description, Instant creationDate, Integer accountTypeId,
			UserAccount user) {
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.accountTypeId = accountTypeId;
		this.user = user;
	}
}
