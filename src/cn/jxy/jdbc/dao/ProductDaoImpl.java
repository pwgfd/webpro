package cn.jxy.jdbc.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.jxy.jdbc.model.Product;

public class ProductDaoImpl{
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		ProductDaoImpl proDao = context.getBean("productDao",ProductDaoImpl.class);
		System.out.println(proDao.getById(2));
	}

	public List<Product> queryByName(String name) {
		String sql = "select * from product where name like ?";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class),new Object[] {"%" + name + "%"});
		
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		List<Product> proList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class),id);
		return proList.size() > 0 ? proList.get(0) : null;
	}

	public int update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?);";
		return jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}

}
