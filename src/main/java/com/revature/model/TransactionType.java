package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for transaction type lookup table
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Entity
@Table(name = "tx_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String type;
}