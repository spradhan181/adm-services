package com.iter.adm.category.model;

import java.util.ArrayList;



public class CategoryResponse {
	ArrayList<Category> categoryList = new ArrayList<>();

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
