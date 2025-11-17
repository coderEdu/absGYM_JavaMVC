package newgym.controller;

import javax.swing.JOptionPane;

import newgym.model.AbsModel;
import newgym.view.NewSessWindow;

public class ProcessNewSessData {
	
	public ProcessNewSessData(NewSessWindow win, String numSess, String cantRep, String sessTime) {
		// TODO Auto-generated constructor stub
		AbsModel abs = new AbsModel();
		int rowsAffected = abs.insertNewSession(numSess, cantRep, sessTime);
		
		if (rowsAffected > 0) {			
			JOptionPane.showMessageDialog(null, rowsAffected + " session(s) inserted.");	
			win.getCreateSessBtn().setEnabled(false);
		}
	}

}
