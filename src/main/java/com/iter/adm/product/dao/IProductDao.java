package com.iter.adm.product.dao;

import java.util.List;

import com.iter.adm.product.model.Product;

public interface IProductDao {

	List<Product> retrieveProducts(String productCode, String item);

}
