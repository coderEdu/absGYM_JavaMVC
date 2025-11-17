package newgym.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import newgym.view.MainWindow;

public class RefreshActivityJCombo implements ActionListener {
	
	MainWindow mainWindow;
	
	public RefreshActivityJCombo(MainWindow mainWindow) {
		// TODO Auto-generated constructor stub
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LoadActivityCombo load = new LoadActivityCombo(mainWindow);
		load.fillSessionData(load.getActivityID());
	}

}
