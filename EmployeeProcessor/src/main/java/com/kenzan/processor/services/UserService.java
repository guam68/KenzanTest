package com.kenzan.processor.services;

import com.kenzan.processor.entities.User;

public interface UserService {
	User authenticateUser(User user);
}
