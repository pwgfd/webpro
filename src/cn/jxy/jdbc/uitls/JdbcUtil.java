package cn.jxy.jdbc.uitls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.print.attribute.standard.PresentationDirection;

public class JdbcUtil {
	
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
					}
				} catch (Exception e) { 
					throw new RuntimeException(e);
				}
			}
		}

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo","root","root");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(JdbcUtil.getConnection());
		System.out.println(JdbcUtil.getConnection());
	}

}