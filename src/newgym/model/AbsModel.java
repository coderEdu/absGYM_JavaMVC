package newgym.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import newgym.controller.StaticResources;

public class AbsModel {
	
	Conn conn;
	
	public AbsModel() {
		// TODO Auto-generated constructor stub
		conn = new Conn();
	}
	
	public int insertNewSession(String numSess, String cantRep, String sessTime) {
		int rowsAffected = 0;
		String sql = "INSERT INTO abs(num_sess, cant_rep, sess_time, id_activity) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.getConnection().prepareStatement(sql);
			// parameters
			preparedStmt.setInt(1, Integer.parseInt(numSess));
			preparedStmt.setInt(2, Integer.parseInt(cantRep));
			preparedStmt.setString(3, sessTime);
			preparedStmt.setInt(4, StaticResources.getLastSelectedID());
			
			rowsAffected = preparedStmt.executeUpdate();
			conn.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	public ResultSet getSessData(int id) {
		ResultSet rs = null;
		String sql = "SELECT num_sess, cant_rep, sess_time, id_activity FROM abs WHERE id_activity = ?";
		try {
			PreparedStatement statement = conn.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			conn.getConnection().close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet biggestNumSession(int id) {
		ResultSet rs = null;
		String sql = "SELECT MAX(num_sess) FROM abs WHERE id_activity = ?";
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement(sql);
			stat.setInt(1, id);
			rs = stat.executeQuery();
			conn.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
