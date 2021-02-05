package com.iter.adm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.model.UserModel;
import com.iter.adm.model.UserRequest;
import com.iter.adm.model.UserResponse;
import com.iter.adm.service.IuserService;

@RestController
public class UserResource {

	@Autowired
	IuserService service;

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/signup")
	public UserResponse getUserInformationfromServiceLayer(@RequestBody UserModel user) {
		UserResponse response = service.getInformationfromServiceClass(user);
		return response;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/signin")
	public UserResponse validateDetailsofUserFromServiceLayer(@RequestBody UserRequest request) {
		UserResponse response = service.validatedetailsofUserFromServiceClass(request.getUsername(),
				request.getPassword());
		return response;
	}

}
