package newgym.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import newgym.view.MainWindow;
import newgym.view.NewActWindow;

public class NewActivity implements ActionListener {

	private MainWindow window;
	
	public NewActivity(MainWindow window) {
		// TODO Auto-generated constructor stub
		this.setWindow(window);
	}

	public MainWindow getWindow() {
		return window;
	}
	
	public void setWindow(MainWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		new NewActWindow();
	}

	
}
