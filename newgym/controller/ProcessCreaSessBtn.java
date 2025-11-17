package newgym.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import newgym.view.NewSessWindow;

public class ProcessCreaSessBtn implements ActionListener {
	
	NewSessWindow win;
	
	public ProcessCreaSessBtn(NewSessWindow win) {
		// TODO Auto-generated constructor stub
		this.setWindow(win);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new ProcessNewSessData(getWin().getTxtNumSess().getText(), getWin().getTxtCantRep().getText(), getWin().getTxtSessTime().getText());
	}

	public NewSessWindow getWin() {
		return win;
	}

	public void setWindow(NewSessWindow win) {
		this.win = win;
	}

}
