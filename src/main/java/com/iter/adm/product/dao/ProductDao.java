package com.iter.adm.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.iter.adm.product.model.Product;

@Component
public class ProductDao implements IProductDao{
	
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Product> retrieveProducts(String productCode, String item) {
		String sql = "select * from products where product_category_code = " +"'"+ productCode +"'" + " and product_name like " + "'%" + item + "%'";
		
		RowMapper<Product> mapRow = new RowMapper<Product>() {
			public Product mapRow(ResultSet rs, int row) throws SQLException {
				Product product = new Product();
				product.setProductName(rs.getString("product_name"));
				product.setProductCategoryCode(rs.getString("product_category_code"));
				product.setProductRating(rs.getDouble("product_rating"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setDiscountPrice(rs.getDouble("discount_price"));
				product.setInventoryStatus(rs.getString("inventory_status"));
				product.setEcommerceName(rs.getString("ecommerce_name"));
				product.setMaxRetailPrice(rs.getDouble("max_retail_price"));
				product.setEcommerceLink(rs.getString("ecommerce_link"));
				product.setEcommerceImage(rs.getString("ecommerce_image"));
				product.setProductDescription(rs.getString("product_description"));
				product.setProductImage(rs.getString("product_image"));
				return product;
			}
		};
		List<Product> result = template.query(sql, mapRow);
		return result;
	}

}
