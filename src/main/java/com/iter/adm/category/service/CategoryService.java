package com.iter.adm.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iter.adm.category.dao.ICategoryDao;
import com.iter.adm.category.model.CategoryResponse;

@Component
public class CategoryService implements ICategoryService {
	@Autowired
	ICategoryDao dao;

	@Override
	public CategoryResponse getCategoryDetails() {
		return dao.getCategoryDetails();

	}

}
