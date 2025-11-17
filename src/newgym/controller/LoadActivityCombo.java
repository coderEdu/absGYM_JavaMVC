package newgym.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;
import newgym.model.AbsModel;
import newgym.model.ActivityModel;
import newgym.view.MainWindow;

public class LoadActivityCombo implements WindowListener {
	
	private MainWindow window;
	
	public LoadActivityCombo(MainWindow window) {
		// TODO Auto-generated constructor stub
		this.setWindow(window); 
	}
	
	public void customizingTable() {
		// Create a renderer for centering
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Apply the renderer to a specific column
		TableColumn idColumn = getWindow().getTable().getColumnModel().getColumn(0);
		TableColumn repsColumn = getWindow().getTable().getColumnModel().getColumn(1);
		TableColumn sessTimeColumn = getWindow().getTable().getColumnModel().getColumn(2);
		TableColumn idActColumn = getWindow().getTable().getColumnModel().getColumn(3);
		idColumn.setCellRenderer(cellRenderer);
		repsColumn.setCellRenderer(cellRenderer);
		sessTimeColumn.setCellRenderer(cellRenderer);
		idActColumn.setCellRenderer(cellRenderer);
	}
	
	public void fillSessionData(int id) {
		AbsModel abs = new AbsModel();
		ResultSet rs = abs.getSessData(id);
		getWindow().getTable().setModel(DbUtils.resultSetToTableModel(rs));
		customizingTable();
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillCombo() {	
		// remove all combo's items
		getWindow().getActivity().removeAllItems();
		
		ActivityModel activity = new ActivityModel();
		ResultSet rs = activity.getBeginFromActivity();
		
		try {
			while (rs.next()) {
				Timestamp activityDate = rs.getTimestamp(1);
				getWindow().getActivity().addItem(activityDate.toString());
			}
			rs.close();
			
			// Set the last item added as the selected item
			int count = getWindow().getActivity().getItemCount();
			if (count > 0) {
				getWindow().getActivity().setSelectedIndex(count-1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Timestamp[] getBeginFinish(int id) {
		Timestamp begin = null, finish = null;

		try {			
			ActivityModel activity = new ActivityModel();
			ResultSet rs = activity.getActivityPeriodByID(id);

			while (rs.next()) {
				begin = rs.getTimestamp(1);
				finish = rs.getTimestamp(2);
			}
			
			rs.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		Timestamp[] period = {begin, finish};
		return period;
	}
	
	public int getActivityID() {
	    HashMap<String, Integer> mapID = new HashMap<>();	
		try {
			ActivityModel activity = new ActivityModel();
			ResultSet rs = activity.getActivities();
			
			// fill the hash-map <Activity_ID, Combo_item_index>
			int counter = 0;
			while (rs.next()) {
				mapID.put(rs.getString(1), counter);
				counter++;
			}
			
			// getting data from hash-map
			String foundKey = null;
			// Iterate through the entry set of the HashMap
	        for (Map.Entry<String, Integer> entry : mapID.entrySet()) {
	            // Compare the value of the current entry with the target value
	            if (entry.getValue().equals(getWindow().getActivity().getSelectedIndex())) {
	                foundKey = entry.getKey();
	                // If you only need the first key found, you can break here
	                // If multiple keys can have the same value, you might want to store them in a list
	                break; 
	            }
	        }	
	        
			rs.close();
			
			if (foundKey != null) {
				StaticResources.setLastSelectedID(Integer.parseInt(foundKey));
				return StaticResources.getLastSelectedID();
			}		
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return 0;
	}

	public MainWindow getWindow() {
		return window;
	}

	public void setWindow(MainWindow window) {
		this.window = window;
	}	

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		fillCombo();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		fillSessionData(getActivityID());
		
		if (StaticResources.isRefreshCombo()) {
			StaticResources.setRefreshCombo(false);
			fillCombo();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
