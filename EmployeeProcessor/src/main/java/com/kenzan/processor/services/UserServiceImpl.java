package com.kenzan.processor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kenzan.processor.entities.User;
import com.kenzan.processor.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User registerUser(User user) {
		String encoded = encoder.encode(user.getPassword());
		user.setPassword(encoded);
		return repo.saveAndFlush(user);
	}
}
