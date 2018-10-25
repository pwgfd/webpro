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
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		daoImpl.delete(1);
	}
	
	@Override
	protected Product getRow(ResultSet rs) {
		// 获取当前记录的数据
		Product product = new Product();
		try {
			product.setDate(rs.getDate("date"));
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setRemark(rs.getString("remark"));
			product.setPrice(rs.getDouble("price"));
		} catch (SQLException e) {
//			throw new RuntimeException(e);
		}
		
		return null;
	}

	public List<Product> queryByName(String name) {
		String sql = "select * from product where name like ?";
		List<Product> proList = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			rs = prep.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setDate(rs.getDate("date"));
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setRemark(rs.getString("remark"));
				product.setPrice(rs.getDouble("price"));
				proList.add(product);
			}
			return proList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, prep, conn);
		}
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		Product product = null;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
			rs = prep.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setDate(rs.getDate("date"));
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setRemark(rs.getString("remark"));
				product.setPrice(rs.getDouble("price"));
			}
			return product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, prep, conn);
		}
	}

	public int update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		return super.execute(sql,new Object[] 
				{product.getName(),product.getPrice(),product.getRemark(),product.getId()});
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.execute(sql,id);
	}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?);";
		return super.execute(sql, new Object[] 
				{product.getName(),product.getPrice(),product.getRemark()});
	}

}
