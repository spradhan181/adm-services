package com.iter.adm.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.iter.adm.category.model.Category;
import com.iter.adm.category.model.CategoryResponse;

@Component
public class CategoryDao implements ICategoryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public CategoryResponse getCategoryDetails() {
		CategoryResponse categoryResponse = new CategoryResponse();
		RowMapper<Category> mapRow = new RowMapper<Category>() {
			public Category mapRow(ResultSet rs, int row) throws SQLException {
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				return category;
			}
		};

		List<Category> result = jdbcTemplate.query("select * from category", mapRow);
		categoryResponse.setCategoryList(result);
		return categoryResponse;
	}

}
