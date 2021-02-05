package com.iter.adm.service;

import com.iter.adm.model.UserModel;
import com.iter.adm.model.UserResponse;

public interface IuserService {
public UserResponse getInformationfromServiceClass(UserModel user);

public UserResponse validatedetailsofUserFromServiceClass(String emailId, String password);
}
