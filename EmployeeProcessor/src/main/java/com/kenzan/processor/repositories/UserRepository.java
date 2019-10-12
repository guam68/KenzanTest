package com.kenzan.processor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kenzan.processor.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findUserByUsername(String username);
}
