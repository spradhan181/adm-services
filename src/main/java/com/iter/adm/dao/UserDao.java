package com.iter.adm.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.iter.adm.model.UserModel;
import com.iter.adm.model.UserResponse;

@Component
public class UserDao implements IuserDAO {

	@Override
	public UserResponse getInformationFromDaoClass(UserModel user) {
		Connection connect = null;
		UserResponse response = new UserResponse();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_ADM?user=root&password=Sudhakar181@12");
			Statement stmt = connect.createStatement();

			PreparedStatement ps = connect.prepareStatement("INSERT INTO UserLoginDetails values (?,?,?,?,?,?) ");

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmailId());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getGender());
			ps.setString(6, user.getUserId());
			ps.executeUpdate();
			response.setResult("User Data Addedd Successfully");
			response.setUserName(user.getEmailId());
		} catch (SQLException e) {
			response.setResult("Error While Saving Record");
			e.printStackTrace();
		}

		return response;

	}

	@Override
	public UserResponse validateDetailsOfuserFromDaoClass(String emailId, String password) {
		Connection connect = null;
		Statement statement = null;
		ResultSet res = null;
		UserResponse response = new UserResponse();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ADM?user=root&password=Sudhakar181@12");
			statement = connect.createStatement();

			res = statement.executeQuery("select * from UserLoginDetails where emailId = " + "'" + emailId + "'"
					+ " and  User_Password = " + "'" + password + "'" + ";");

			if (res.next() == true) {
				response.setResult("SUCCESSFULLY_VALIDATED");
			}else {
				response.setResult("NO RECORD FOUND or INVALID");
			}

		} catch (SQLException e) {
			response.setResult("INVALID_CREDENTIAL");
			e.printStackTrace();
		}
		return response;
	}

}
