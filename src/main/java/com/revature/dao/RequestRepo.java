package com.revature.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.BetweenUsers;

public interface RequestRepo extends JpaRepository<BetweenUsers, Integer> {

	public List<BetweenUsers> findAllByUser(String name);
}
