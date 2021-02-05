package com.iter.adm.category.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.category.model.CategoryResponse;
import com.iter.adm.category.service.ICategoryService;

@RestController
public class CategoryRsource {
	@Autowired
	ICategoryService service;

	@GetMapping(path = "/details")
	@CrossOrigin(origins = "*")
	public CategoryResponse getCategoryDetailsfromServiceLayer() {
		return service.getCategoryDetailsFromServiceClass();

	}

}
