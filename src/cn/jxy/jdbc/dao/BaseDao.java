package cn.jxy.jdbc.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.jxy.jdbc.model.Product;
import cn.jxy.jdbc.uitls.JdbcUtil;

// 公共的dao访问父类,所有dao子类都需要继承
public class BaseDao<T> {
	
	protected List<T> query(Class<T> clazz,String sql,Object... param) {
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
			// 首先获取列的信息
			ResultSetMetaData metaData = rs.getMetaData();
			// 游标向下移动一行
			while (rs.next()) {
				T model = clazz.newInstance();
				// 此处要循环获取所有列名称
				for(int index=1;index<=metaData.getColumnCount();index++) {
					// 通过列的索引(从1开始)获取列的名称,
					String colName = metaData.getColumnName(index);
					System.out.println("colName:" + colName);
					// 通过列名获取相应的类的字段名称
					Field field = clazz.getDeclaredField(colName);
					field.setAccessible(true);
					field.set(model, rs.getObject(colName));
				}
				proList.add(model);
			}
			return proList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, prep, conn);
		}
	}
	
	// insert delete update
	protected int execute(String sql,Object... param) {
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
