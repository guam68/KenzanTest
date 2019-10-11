package com.kenzan.processor.services;

import java.util.Optional;

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
	public User authenticateUser(User user) {
		Optional<User> userOpt = repo.findById(user.getId());
		User managed = null;
		
		if (userOpt.isPresent()) {
			managed = userOpt.get();
			
			if (encoder.matches(user.getPassword(), managed.getPassword())) {
				return managed;
			}
		}
		
		return null;
	}
}
