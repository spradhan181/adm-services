package com.iter.adm.user.dao;

import com.iter.adm.user.model.CreateUserRequest;
import com.iter.adm.user.model.CreateUserResponse;
import com.iter.adm.user.model.UserRequest;
import com.iter.adm.user.model.UserResponse;

public interface IUserDAO {


	public CreateUserResponse createUser(CreateUserRequest user);

	public UserResponse validateUser(String emailId, String password);

	public UserResponse recoverPassword(UserRequest request);

	public UserResponse updatePassword(UserRequest request);

}
