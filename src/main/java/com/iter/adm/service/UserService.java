package com.iter.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iter.adm.dao.IuserDAO;

import com.iter.adm.dao.UserDao;
import com.iter.adm.model.UserModel;
import com.iter.adm.model.UserResponse;

@Component
public class UserService implements IuserService {
	@Autowired
	IuserDAO dao;

	@Override
	public UserResponse getInformationfromServiceClass(UserModel user) {
		return dao.getInformationFromDaoClass(user);
	}

	@Override
	public UserResponse validatedetailsofUserFromServiceClass(String emailId, String password) {
		return dao.validateDetailsOfuserFromDaoClass(emailId, password);
	}

}
