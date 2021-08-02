package kr.ac.kopo.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			//1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.
			String url = "jdbc:oracle:thin:@192.168.217.202:1521/KOPODA";
			String user = "da2106";
			String pw = "da06";
			conn = DriverManager.getConnection(url,user,pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}
