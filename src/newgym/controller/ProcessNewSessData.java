package newgym.controller;

import javax.swing.JOptionPane;

import newgym.model.AbsModel;

public class ProcessNewSessData {
	
	public ProcessNewSessData(String numSess, String cantRep, String sessTime) {
		// TODO Auto-generated constructor stub
		AbsModel abs = new AbsModel();
		int rowsAffected = abs.insertNewSession(numSess, cantRep, sessTime);
		JOptionPane.showMessageDialog(null, rowsAffected + " session(s) inserted.");	
	}

}
