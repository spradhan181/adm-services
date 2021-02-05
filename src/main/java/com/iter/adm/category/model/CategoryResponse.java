package com.iter.adm.category.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;



public class CategoryResponse {
	List<Category> categoryList = new ArrayList<>();

	public List<Category> getCategoryList() {
		if(CollectionUtils.isEmpty(categoryList)) {
			return new ArrayList<Category>();
		}
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
