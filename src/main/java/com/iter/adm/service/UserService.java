package com.iter.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iter.adm.dao.IUserDAO;
import com.iter.adm.model.CreateUserRequest;
import com.iter.adm.model.CreateUserResponse;
import com.iter.adm.model.UserRequest;
import com.iter.adm.model.UserResponse;

@Component
public class UserService implements IUserService {
	@Autowired
	IUserDAO dao;

	@Override
	public CreateUserResponse createUser(CreateUserRequest user) {
		return dao.createUser(user);
	}

	@Override
	public UserResponse validateUser(String emailId, String password) {
		return dao.validateUser(emailId, password);
	}

	@Override
	public UserResponse recoverPassword(UserRequest request) {
		return dao.recoverPassword(request);
	}

	@Override
	public UserResponse updatePassword(UserRequest request) {
		return dao.updatePassword(request);
	}

}