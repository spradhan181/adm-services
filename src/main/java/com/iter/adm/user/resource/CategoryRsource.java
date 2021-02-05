package com.iter.adm.user.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.user.model.CategoryResponse;
import com.iter.adm.user.service.ICategoryService;

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
