package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Notification;

/**
 * Repo for interacting with bank accounts in the database.
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {

}
