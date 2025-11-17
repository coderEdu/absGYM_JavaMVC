package newgym.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ActivityModel {
	
	Conn conn;
	
	public ActivityModel() {
		// TODO Auto-generated constructor stub
		conn = new Conn();
	}
	
	public int insertNewActivity(Timestamp begin, Timestamp finish) {
		int rowsAffected = 0;
		String sql = "INSERT INTO activity(begin, finish) VALUES (?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.getConnection().prepareStatement(sql);
			// parameters
			preparedStmt.setTimestamp(1, begin);
			preparedStmt.setTimestamp(2, finish);
			rowsAffected = preparedStmt.executeUpdate();
			conn.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;		
	}
	
	public ResultSet getBeginFromActivity() {	
		ResultSet rs = null;
		try {
			Statement statement = conn.getConnection().createStatement();
			rs = statement.executeQuery("SELECT begin FROM activity");
			conn.getConnection().close();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getActivityPeriodByID(int id) {
		ResultSet rs = null;
		String sql = "SELECT begin, finish FROM activity WHERE id = ?";
		PreparedStatement stat;
		try {
			stat = conn.getConnection().prepareStatement(sql);
			stat.setInt(1, id);
			rs = stat.executeQuery();
			conn.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getActivities() {
		ResultSet rs = null;
		Statement miStatement;
		try {
			miStatement = conn.getConnection().createStatement();
			rs = miStatement.executeQuery("SELECT * FROM activity");
			conn.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
