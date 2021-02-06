package com.iter.adm.user.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.user.model.CreateUserRequest;
import com.iter.adm.user.model.CreateUserResponse;
import com.iter.adm.user.model.UserRequest;
import com.iter.adm.user.model.UserResponse;
import com.iter.adm.user.service.IUserService;

@RestController
public class UserResource {

	@Autowired
	IUserService service;

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/signup")
	public CreateUserResponse createUser(@RequestBody CreateUserRequest userRequest) {
		return service.createUser(userRequest);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/signin")
	public UserResponse validateUser(@RequestBody UserRequest request) {
		return service.validateUser(request.getEmail(), request.getPassword());
	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/recover")
	public UserResponse recoverPassword(@RequestBody UserRequest request) {
		return service.recoverPassword(request);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/updatepassword")
	public UserResponse updatePassword(@RequestBody UserRequest request) {
		return service.updatePassword(request);
	}

}
