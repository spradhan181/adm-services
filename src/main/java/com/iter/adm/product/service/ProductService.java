package com.iter.adm.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iter.adm.product.dao.IProductDao;
import com.iter.adm.product.model.Product;

@Component
public class ProductService implements IProductService{
	
	@Autowired
	IProductDao dao;

	@Override
	public List<Product> retrieveProducts(String productCode, String item) {
		List<Product> products = dao.retrieveProducts(productCode, item);
		return products;
	}

}
