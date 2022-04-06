package com.revature.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto object that creates a buffer between input data and the database
 * 
 * @author Cameron, Amir, Chandra
 */
@Data
@AllArgsConstructor
public class TransactionDto {

	private int accountId;
	private int txTypeId;
	private Double amount;
	private String description;
	private Instant creationDate;

}
