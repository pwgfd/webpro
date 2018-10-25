package cn.jxy.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.SignatureProperties;
import javax.xml.transform.sax.SAXSource;

import cn.jxy.jdbc.model.Product;
import cn.jxy.jdbc.uitls.JdbcUtil;

public class ProductDaoImpl extends BaseDao<Product> {
	
	public static void main(String[] args) {
		ProductDaoImpl productDao = new ProductDaoImpl();
		System.out.println(productDao.queryByName(""));
	}

	@Override
	protected Product getRow(ResultSet rs) throws SQLException {
		// 获取当前记录的数据
		Product product = new Product();
		product.setDate(rs.getDate("date"));
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setRemark(rs.getString("remark"));
		product.setPrice(rs.getDouble("price"));
		return product;
	}

	public List<Product> queryByName(String name) {
		String sql = "select * from product where name like ?";
		return super.query(sql, "%" + name + "%");
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		List<Product> proList = super.query(sql, id);
		return proList.size() > 0 ? proList.get(0) : null;
	}

	public int update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		return super.execute(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.execute(sql, id);
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?);";
		return super.execute(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}

}
