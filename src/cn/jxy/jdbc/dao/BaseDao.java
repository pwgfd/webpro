package cn.jxy.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.jxy.jdbc.model.Product;
import cn.jxy.jdbc.uitls.JdbcUtil;

// 公共的dao访问父类,所有dao子类都需要继承
public abstract class BaseDao<T> {
	
	protected abstract T getRow(ResultSet rs);
	
	public List<T> queryByName(String sql,Object... param) {
		List<T> proList = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
			for(int i=0;i<param.length;i++) {
				prep.setObject(i+1, param[i]);
			}
			rs = prep.executeQuery();
			while (rs.next()) {
				
			}
			return proList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, prep, conn);
		}
	}
	
	// insert delete update
	public int execute(String sql,Object... param) {
		System.out.println("abc");
		Connection conn = null; 
		PreparedStatement prep = null;
		try {
			conn = JdbcUtil.getConnection();
			// 可以生成预编译的SQL语句,在多条记录插入的时候效率高,而且解决了SQL注入问题.
			prep = conn.prepareStatement(sql);
//			prep.setString(1, product.getName());
//			prep.setDouble(2, product.getPrice());
//			prep.setString(3, product.getRemark());
//			prep.setInt(4, product.getId());
			for(int i=0;i<param.length;i++) {
				prep.setObject(i+1, param[i]);
			}
			return prep.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(prep, conn);
		}
	}

}
