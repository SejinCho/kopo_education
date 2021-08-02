package kr.ac.kopo.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCClose {
	
	public static void close(Connection conn, Statement pstmt) {
		//일반적으로 Prepared Statement 객체를 더 많이 쓴다. 
		//그러나 때에 따라서 Statement를 사용할 수 있다.
		//그래서 Statement를 상속받는 Prepared Statement는 묵시적 형변환이 가능하므로
		//Statement를 사용
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
