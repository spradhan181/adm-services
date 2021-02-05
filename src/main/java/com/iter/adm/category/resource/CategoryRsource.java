package com.iter.adm.category.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.category.model.CategoryResponse;
import com.iter.adm.category.service.ICategoryService;

@RestController
public class CategoryRsource {

	@Autowired
	ICategoryService service;

	/**
	 * This method return list of categories
	 * 
	 * @return
	 */
	@GetMapping(path = "/getcategories")
	@CrossOrigin(origins = "*")
	public CategoryResponse getCategoryDetails() {
		return service.getCategoryDetails();
	}
	
	@PutMapping(path ="/addcategories")
	public String addcategories() {
		return "Success";
	}

}
