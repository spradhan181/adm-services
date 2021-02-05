package com.iter.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iter.adm.dao.IcategoryDao;
import com.iter.adm.model.CategoryResponse;

@Component
public class CategoryService implements IcategoryService {
	@Autowired
	IcategoryDao dao;

	@Override
	public CategoryResponse getCategoryDetailsFromServiceClass() {
	return dao.getCategoryDetailsfromServiceClass();
		
		
	}

}
