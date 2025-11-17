package newgym.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import newgym.view.NewSessWindow;

public class LoadNewSessWindow implements ActionListener {
	
	public LoadNewSessWindow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new NewSessWindow();
	}

}
