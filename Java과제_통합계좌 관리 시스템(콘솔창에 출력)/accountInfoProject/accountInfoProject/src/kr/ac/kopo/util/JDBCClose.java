package kr.ac.kopo.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCClose {
	public static void close(Connection conn, Statement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
