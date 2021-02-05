package com.iter.adm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.model.CategoryResponse;
import com.iter.adm.service.IcategoryService;

@RestController
public class CategoryRsource {
	@Autowired
	IcategoryService service;

	@GetMapping(path = "/details")
	@CrossOrigin(origins = "*")
	public CategoryResponse getCategoryDetailsfromServiceLayer() {
		return service.getCategoryDetailsFromServiceClass();

	}

}
