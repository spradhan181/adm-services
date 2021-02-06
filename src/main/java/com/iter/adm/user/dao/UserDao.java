package com.iter.adm.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.iter.adm.user.model.CreateUserRequest;
import com.iter.adm.user.model.CreateUserResponse;
import com.iter.adm.user.model.UserData;
import com.iter.adm.user.model.UserRequest;
import com.iter.adm.user.model.UserResponse;
import com.mysql.cj.util.StringUtils;

@Component
public class UserDao implements IUserDAO {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/adm?user=root&password=Future@123";
	private static final String USER_INSERT_QUERY = "INSERT INTO user_login_details values (?,?,?,?,?,?) ";
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
			ps.setInt(6, 0);

			ps.execute();
			response.setResult(SUCCESS);
			response.setUserId(userRequest.getEmailId());
		} catch (SQLIntegrityConstraintViolationException e) {
			response.setResult(DUPLICATE);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
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

		String sqlUrl = null;

		if (!StringUtils.isNullOrEmpty(password)) {
			sqlUrl = "select * from user_login_details where email = " + "'" + emailId + "'" + " and  user_password = "
					+ "'" + password + "'";
		} else {
			sqlUrl = "select * from user_login_details where email = " + "'" + emailId + "'";
		}

		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(CONNECTION_URL);
			statement = connect.createStatement();
			res = statement.executeQuery(sqlUrl);

			if (res.next() == true) {
				UserData user = new UserData();
				user.setEmailId(res.getString("email"));
				user.setFirstName(res.getString("first_Name"));
				user.setLastName(res.getString("last_Name"));
				user.setGender(res.getString("gender"));
				response.setUserData(user);
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
		UserResponse response = null;
		if (request.getOtp() == 0) {
			response = validateUser(request.getEmail(), null);
		} else {
			response = new UserResponse();
			response.setResult(verifyOtp(request));
		}
		return response;

	}

	private String verifyOtp(UserRequest request) {
		Connection connect = null;
		Statement statement = null;
		ResultSet res = null;

		String sqlUrl = "select * from user_login_details where email = " + "'" + request.getEmail() + "'"
				+ " and  otp = " + "'" + request.getOtp() + "'";

		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(CONNECTION_URL);
			statement = connect.createStatement();
			res = statement.executeQuery(sqlUrl);

			if (res.next() == true) {
				return SUCCESS;
			} else {
				return INVALID;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return ERROR;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Override
	public UserResponse updatePassword(UserRequest userRequest, boolean isOtpNeedsToBeSet) {
		Connection connect = null;
		Statement statement = null;
		UserResponse response = new UserResponse();
		String updateSql = null;
		if (!isOtpNeedsToBeSet) {
			updateSql = "update user_login_details set user_password = " + "'" + userRequest.getPassword() + "'"
					+ " , otp = " + null + " where email = " + "'" + userRequest.getEmail() + "'";
		} else {
			updateSql = "update user_login_details set otp = " + userRequest.getOtp() + " where email = " + "'"
					+ userRequest.getEmail() + "'";
		}

		try {
			Class.forName(DRIVER_NAME);
			connect = DriverManager.getConnection(CONNECTION_URL);
			statement = connect.createStatement();
			statement.executeUpdate(updateSql);
			response.setResult(SUCCESS);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.setResult(ERROR);
		} catch (SQLException ex) {
			ex.printStackTrace();
			response.setResult(ERROR);
		}

		return response;
	}

	@Override
	public UserResponse updateOTP(String email, int otp) {
		UserRequest request = new UserRequest();
		request.setEmail(email);
		request.setOtp(otp);
		return updatePassword(request, true);
	}

}
