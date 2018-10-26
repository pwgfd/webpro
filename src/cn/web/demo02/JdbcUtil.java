package cn.web.demo02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.PresentationDirection;

public class JdbcUtil {
	// 用来存储当前线程的局部变量
	private static ThreadLocal<Connection> cLocal = new ThreadLocal<Connection>();
	
	private JdbcUtil() {}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(PreparedStatement prep,
			Connection conn) {
		close(null, prep, conn);
	}

	public static void close(ResultSet rs, PreparedStatement prep,
			Connection conn) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (prep != null && !prep.isClosed()) {
					prep.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
//						cLocal.set(null);
					}
				} catch (Exception e) { 
					throw new RuntimeException(e);
				}
			}
		}

	}

	public static Connection getConnection() {
		// 判断当前线程中是否已存在Connection
		Connection conn= cLocal.get();
		if(conn==null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo","root","root");
				cLocal.set(conn);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		Connection connection = JdbcUtil.getConnection();
		System.out.println(connection);
		close(null, connection);
		connection = JdbcUtil.getConnection();
		System.out.println(connection);
		System.out.println(connection.isClosed());
	}

}