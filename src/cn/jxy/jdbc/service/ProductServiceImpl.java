package cn.jxy.jdbc.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jxy.jdbc.dao.ProductDaoImpl;
import cn.jxy.jdbc.model.Product;

public class ProductServiceImpl implements ProductService {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-*.xml");
		ProductService proService = context.getBean("productService", ProductService.class);
		Product product = new Product();
		product.setName("小米3");
		proService.save(product);
	}

	private ProductDaoImpl productDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jxy.jdbc.service.ProductService#setProductDao(cn.jxy.jdbc.dao.
	 * ProductDaoImpl)
	 */
	@Override
	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jxy.jdbc.service.ProductService#queryByName(java.lang.String)
	 */
	@Override
	public List<Product> queryByName(String name) {
		return productDao.queryByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jxy.jdbc.service.ProductService#getById(int)
	 */
	@Override
	public Product getById(int id) {
		return productDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jxy.jdbc.service.ProductService#update(cn.jxy.jdbc.model.Product)
	 */
	@Override
	public int update(Product product) {
		return productDao.update(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jxy.jdbc.service.ProductService#delete(int)
	 */
	@Override
	public int delete(int id) {
		return productDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jxy.jdbc.service.ProductService#save(cn.jxy.jdbc.model.Product)
	 */
	@Override
	public int save(Product product) {
		int count = productDao.save(product);
		// Integer.parseInt("xxxxx");
		return count;
	}

}
