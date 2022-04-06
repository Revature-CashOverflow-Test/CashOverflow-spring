package com.revature.dto;

import java.time.Instant;

import lombok.Data;

/**
 * Bank account model that we will return to the client
 * 
 * @author Parker Mace
 */
@Data
public class BankAccountDto {
	Integer id;
	String name;
	Double balance;
	String description;
	Instant creationDate;
	Integer accountTypeId;
}
