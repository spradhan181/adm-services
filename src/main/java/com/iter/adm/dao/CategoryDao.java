package com.iter.adm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.iter.adm.model.Category;
import com.iter.adm.model.CategoryResponse;
import com.iter.adm.model.UserResponse;

@Component
public class CategoryDao implements IcategoryDao {

	@Override
	public CategoryResponse getCategoryDetailsfromServiceClass() {
		Connection connect = null;
		Statement state = null;
		ResultSet res = null;
		CategoryResponse response = new CategoryResponse();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_ADM?user=root&password=Sudhakar181@12");
			state=connect.createStatement();
			res=state.executeQuery("Select * from category");
		}catch(

	SQLException e)
	{

		e.printStackTrace();
	}
		
	ArrayList<Category>  catlist = new ArrayList<>();
	try {
		while(res.next()==true) {
		
			Category cat = new Category();
			cat.setCategoryId(res.getString("category_id"));
			cat.setCategoryName(res.getString("category_name"));
			catlist.add(cat);
			response.setCategoryList(catlist);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return response;
}
	
}
