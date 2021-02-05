package com.iter.adm.dao;

import com.iter.adm.model.UserModel;
import com.iter.adm.model.UserResponse;

public interface IuserDAO {
	public UserResponse getInformationFromDaoClass(UserModel user);

	public UserResponse validateDetailsOfuserFromDaoClass(String emailId, String password);

}
