package com.iter.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iter.adm.dao.ICategoryDao;
import com.iter.adm.model.CategoryResponse;

@Component
public class CategoryService implements ICategoryService {
	@Autowired
	ICategoryDao dao;

	@Override
	public CategoryResponse getCategoryDetailsFromServiceClass() {
	return dao.getCategoryDetailsfromServiceClass();
		
		
	}

}
