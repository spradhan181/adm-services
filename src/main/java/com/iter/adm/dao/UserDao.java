package com.iter.adm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.iter.adm.model.CreateUserRequest;
import com.iter.adm.model.CreateUserResponse;
import com.iter.adm.model.UserRequest;
import com.iter.adm.model.UserResponse;
import com.mysql.cj.util.StringUtils;

@Component
public class UserDao implements IUserDAO {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/adm?user=root&password=Future@123";
	private static final String USER_INSERT_QUERY = "INSERT INTO user_login_details values (?,?,?,?,?) ";
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String SUCCESS = "Success";
	private static final String ERROR = "Error";
	private static final String INVALID = "Invalid";
	private static final String DUPLICATE = "Duplicate";
	
	
	@Override
	public CreateUserResponse createUser(CreateUserRequest userRequest) {
		Connection connect = null;
		CreateUserResponse response = new CreateUserResponse();
		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(CONNECTION_URL);
			PreparedStatement ps = connect.prepareStatement(USER_INSERT_QUERY);
			ps.setString(1, userRequest.getFirstName());
			ps.setString(2, userRequest.getLastName());
			ps.setString(3, userRequest.getEmailId());
			ps.setString(4, userRequest.getPassword());
			ps.setString(5, userRequest.getGender());

			ps.execute();
			response.setResult(SUCCESS);
			response.setUserId(userRequest.getEmailId());
		} catch (SQLIntegrityConstraintViolationException e) {
			response.setResult(DUPLICATE);
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException ex) {
			response.setResult(ERROR);
			ex.printStackTrace();
		}

		return response;

	}

	@Override
	public UserResponse validateUser(String emailId, String password) {
		Connection connect = null;
		Statement statement = null;
		ResultSet res = null;
		UserResponse response = new UserResponse();
		
		String sqlUrl = null ;
		
		if(!StringUtils.isNullOrEmpty(password)) {
			sqlUrl = "select * from user_login_details where email = " + "'" + emailId + "'"
					+ " and  user_password = " + "'" + password + "'" ;
		} else {
			sqlUrl = "select * from user_login_details where email = " + "'" + emailId + "'" ;
		}
		
	
		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(CONNECTION_URL);
			statement = connect.createStatement();
			res = statement.executeQuery(sqlUrl);

			if (res.next() == true) {
				response.setResult(SUCCESS);
			} else {
				response.setResult(INVALID);
			}

		} catch (SQLException e) {
			response.setResult(ERROR);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public UserResponse recoverPassword(UserRequest request) {
		UserResponse response = validateUser(request.getUserName(), null);
		return response;
	}

	@Override
	public UserResponse updatePassword(UserRequest userRequest) {
		Connection connect = null;
		Statement statement = null;
		UserResponse response = new UserResponse();
		String updateSql = "update user_login_details set user_password = " + "'" + userRequest.getPassword() + "'" +
				" where email = " + "'" + userRequest.getUserName() + "'";
		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(CONNECTION_URL);
			statement = connect.createStatement();
			statement.executeUpdate(updateSql);
			response.setResult(SUCCESS);
			
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.setResult(ERROR);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			response.setResult(ERROR);
		}

		return response;
	}

}
