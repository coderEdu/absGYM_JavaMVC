package newgym.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import newgym.controller.CreateNewSess;
import newgym.controller.LoadActivityCombo;
import newgym.controller.NewActivity;
import newgym.controller.RefreshActivityJCombo;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> activityJCombo;
	private JButton newActivity; 
	private JButton newSession;
	private JPanel menus;
	private JPanel btnPanel;
	private JTable table;

	public MainWindow() {
		// TODO Auto-generated constructor stub
		setTitle("Edu's Abs GYM - Main");
		int width = 350, height = 350;
		setBounds(1920/2-width/2, 1080/2-height/2, width, height);
		
		setLayout(new BorderLayout());
		menus = new JPanel();
		menus.setLayout(new FlowLayout());
		
		activityJCombo = new JComboBox<String>();
		activityJCombo.setEditable(false);
		activityJCombo.addActionListener(new RefreshActivityJCombo(this));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 320, 300);
		add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(24);
		scrollPane.setViewportView(table);		
		
		menus.add(activityJCombo);
		
		add(menus, BorderLayout.NORTH);
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1, 2));
		
		// button newActivity
		newActivity = new JButton("Nueva actividad");
		newActivity.addActionListener(new NewActivity(this));
		
		// button newSession
		newSession = new JButton("Nueva session");
		newSession.addActionListener(new CreateNewSess(this));
		
		btnPanel.add(newActivity);
		btnPanel.add(newSession);
		
		add(btnPanel, BorderLayout.SOUTH);
		
		addWindowListener(new LoadActivityCombo(this));
	}
	
	public JComboBox<String> getActivity() {
		return activityJCombo;
	}

	public void setActivity(JComboBox<String> activityJCombo) {
		this.activityJCombo = activityJCombo;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
