package newgym.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import java.sql.Timestamp;

import newgym.model.ActivityModel;
import newgym.view.NewActWindow;

public class ProcessNewActData implements ActionListener {
	
	NewActWindow window;
	
	public ProcessNewActData(NewActWindow window) {
		// TODO Auto-generated constructor stub
		setWindow(window);
	}

	public NewActWindow getWindow() {
		return window;
	}

	public void setWindow(NewActWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// getting data
		int y = Integer.parseInt(getWindow().getComboYear().getSelectedItem().toString());
		int m = getWindow().getSelectedMonth(getWindow().getComboMonth());
		int d = Integer.parseInt(getWindow().getComboDay().getSelectedItem().toString());
		int begHour = Integer.parseInt(getWindow().getComboHour1().getSelectedItem().toString());
		int finHour = Integer.parseInt(getWindow().getComboHour2().getSelectedItem().toString());
		int begMin = Integer.parseInt(getWindow().getComboMin1().getSelectedItem().toString());
		int finMin = Integer.parseInt(getWindow().getComboMin2().getSelectedItem().toString());
		
        GregorianCalendar begin = new GregorianCalendar(y, m, d, begHour, begMin, 00);
        GregorianCalendar finish = new GregorianCalendar(y, m, d, finHour, finMin, 00);
        
        Timestamp beginTS = new Timestamp(begin.getTimeInMillis());
        Timestamp finishTS = new Timestamp(finish.getTimeInMillis());
		
        ActivityModel activity = new ActivityModel();			
        int rowsAffected = activity.insertNewActivity(beginTS, finishTS);
        if (rowsAffected > 0) {        	
        	StaticResources.setRefreshCombo(true);
        	JOptionPane.showMessageDialog(null, rowsAffected + " activity(s) inserted.");
        	getWindow().getBtnNewActiv().setEnabled(false);
        }
	}

}
