package com.iter.adm.service;

import com.iter.adm.model.CreateUserRequest;
import com.iter.adm.model.CreateUserResponse;
import com.iter.adm.model.UserRequest;
import com.iter.adm.model.UserResponse;

public interface IUserService {

public CreateUserResponse createUser(CreateUserRequest userRequest);

public UserResponse validateUser(String username, String password);

public UserResponse recoverPassword(UserRequest request);

public UserResponse updatePassword(UserRequest request);
}
