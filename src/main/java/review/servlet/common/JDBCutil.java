package review.servlet.common;

import java.sql.*;

public class JDBCutil {
	static final String URL = "jdbc:mysql://localhost:3306/springdb";
	static final String USER="root";
	static final String PW="02140607";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PW);
		} catch(ClassNotFoundException | SQLException e) {
			// ClassNotFoundException : Class.forName(...)에서 지정한 클래스를 찾지 못했을 때
			//SQLException : JDBC로 DB 작업 중 문제가 생겼을 때
			e.printStackTrace();
		}
		return null;
	}
	
	// close할 상황마다 매개변수를 만들어 메서드 오버로딩 활용함
	public static void close(PreparedStatement pt, Connection con) {
		try {
			if (pt != null) {
				pt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement pt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pt != null) {
				pt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
