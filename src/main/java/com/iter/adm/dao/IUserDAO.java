package com.iter.adm.dao;

import com.iter.adm.model.CreateUserRequest;
import com.iter.adm.model.CreateUserResponse;
import com.iter.adm.model.UserRequest;
import com.iter.adm.model.UserResponse;

public interface IUserDAO {


	public CreateUserResponse createUser(CreateUserRequest user);

	public UserResponse validateUser(String emailId, String password);

	public UserResponse recoverPassword(UserRequest request);

	public UserResponse updatePassword(UserRequest request);

}
