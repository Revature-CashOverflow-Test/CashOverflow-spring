package com.revature.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Transaction;

/**
 * Repo for interacting with bank accounts in the database.
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	public List<Transaction> findAllByAccountIdOrderByCreationDateDesc(Integer id);

}
