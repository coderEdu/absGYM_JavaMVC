package newgym.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	
	private Connection conn;
	
	public Connection getConnection() {
		// create connection
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newgym", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
