package com.iter.adm.product.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.product.model.Product;
import com.iter.adm.product.service.IProductService;

@RestController
public class ProductResource {
	
	@Autowired
	IProductService service;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getproduct/{productcode}/{item}")
	public List<Product> getProducts(@PathVariable(value = "productcode") String productCode, @PathVariable(value = "item") String item){
		List<Product> products = service.retrieveProducts(productCode, item);
		return products;
	}

}
