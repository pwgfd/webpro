package cn.jxy.jdbc.service;

import java.util.List;

import cn.jxy.jdbc.dao.ProductDaoImpl;
import cn.jxy.jdbc.model.Product;

public interface ProductService {

	void setProductDao(ProductDaoImpl productDao);

	List<Product> queryByName(String name);

	Product getById(int id);

	int update(Product product);

	int delete(int id);

	int save(Product product);

}