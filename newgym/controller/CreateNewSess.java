package newgym.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import newgym.model.AbsModel;
import newgym.view.MainWindow;
import newgym.view.NewSessWindow;

public class CreateNewSess implements ActionListener {
	
	private MainWindow window;
	
	public CreateNewSess(MainWindow window) {
		// TODO Auto-generated constructor stub
		setWindow(window);
	}

	public MainWindow getWindow() {
		return window;
	}

	public void setWindow(MainWindow window) {
		this.window = window;
	}

	public int getBiggestNumSess(int id) {
		int numSess = 1;
		try {
			AbsModel abs = new AbsModel();
			ResultSet rs = abs.biggestNumSession(id);

			while (rs.next()) {
				numSess = rs.getInt(1) + 1;	
			}
			
			rs.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return numSess;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		LoadActivityCombo activityCombo = new LoadActivityCombo(window);
		int activityID = activityCombo.getActivityID();
		Timestamp[] period = activityCombo.getBeginFinish(activityID);
		
		Timestamp tBegin = period[0];
		Timestamp tFinish = period[1];
		

		Date dbegin = new Date(tBegin.getTime());
		Date dFinish = new Date(tFinish.getTime());	
	        
        if (System.currentTimeMillis() > dFinish.getTime()) {
        	JOptionPane.showMessageDialog(window, "Tu hora de entrenamiento ya terminó.", "Tiempo Cumplido", JOptionPane.INFORMATION_MESSAGE);
        	//System.out.println("\nTime Out!");
        } else if (System.currentTimeMillis() >= dbegin.getTime()) {
        	// show the new session window
        	new NewSessWindow(getBiggestNumSess(activityID));
        	//System.out.println("\nOn Time");
        } else {
        	JOptionPane.showMessageDialog(window, "Aún no es hora de entrenar!!", "Ops ...",
        			JOptionPane.INFORMATION_MESSAGE);
        	//System.out.println("\nNot Yet!");
        }
	}

}


















