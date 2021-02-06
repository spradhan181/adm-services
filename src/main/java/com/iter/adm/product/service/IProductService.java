package com.iter.adm.product.service;

import java.util.List;

import com.iter.adm.product.model.Product;

public interface IProductService {

	List<Product> retrieveProducts(String productCode, String item);

}
