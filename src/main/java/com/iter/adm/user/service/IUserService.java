package com.iter.adm.user.service;

import com.iter.adm.user.model.CreateUserRequest;
import com.iter.adm.user.model.CreateUserResponse;
import com.iter.adm.user.model.UserRequest;
import com.iter.adm.user.model.UserResponse;

public interface IUserService {

public CreateUserResponse createUser(CreateUserRequest userRequest);

public UserResponse validateUser(String username, String password);

public UserResponse recoverPassword(UserRequest request);

public UserResponse updatePassword(UserRequest request);
}
