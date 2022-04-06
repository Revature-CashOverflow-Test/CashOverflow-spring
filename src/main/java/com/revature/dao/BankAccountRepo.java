package com.revature.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.BankAccount;
import com.revature.model.UserAccount;

/**
 * Repo for interacting with bank accounts in the database.
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer> {

	public List<BankAccount> findAllByUserId(Integer id);

	public BankAccount findByUserAndName(UserAccount user, String name);

}
