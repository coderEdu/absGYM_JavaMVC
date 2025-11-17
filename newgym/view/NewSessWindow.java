package newgym.view;

import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import newgym.controller.ProcessCreaSessBtn;

public class NewSessWindow {
	
	private JTextField txtNumSess;
	private JTextField txtCantRep;
	private JTextField txtSessTime;
	
	public NewSessWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public NewSessWindow(int numSess) {
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame("Nueva Sesion");
		frame.setSize(256, 200);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNumSess = new JLabel("Numero de sesion:");
		lblNumSess.setBounds(10, 21, 150, 15);
		JLabel lblCantRep = new JLabel("Numero de repeticiones: ");
		lblCantRep.setBounds(10, 58, 150, 15);
		JLabel lblSessTime = new JLabel("Horario de la sesion:");
		lblSessTime.setBounds(10, 95, 150, 15);
		
		// getting time now
		LocalTime time = LocalTime.now();
		
		txtNumSess = new JTextField(10);
		txtNumSess.setBounds(130, 18, 112, 25);
		txtNumSess.setText("" + numSess);
		txtNumSess.setHorizontalAlignment(JTextField.RIGHT);
		txtNumSess.setEnabled(false);
		
		txtCantRep = new JTextField(10);
		txtCantRep.setBounds(167, 55, 75, 25);
		txtCantRep.setHorizontalAlignment(JTextField.RIGHT);
		
		// parsing hour
		JTextField h = new JTextField(time.getHour());
		if (h.getText().length() == 1)
			h.setText("0" + time.getHour());
		else
			h.setText("" + time.getHour());
		
		// parsing minute
		JTextField m = new JTextField(time.getMinute());
		if (m.getText().length() == 1)
			m.setText("0" + time.getMinute());
		else
			m.setText("" + time.getMinute());

		// setting session-time JTextField
		txtSessTime = new JTextField(h.getText() + ":" + m.getText());
		txtSessTime.setBounds(139, 92, 103, 25);
		txtSessTime.setHorizontalAlignment(JTextField.RIGHT);
		
		frame.add(lblNumSess);
		frame.add(txtNumSess);
		frame.add(lblCantRep);
		frame.add(txtCantRep);
		frame.add(lblSessTime);
		frame.add(txtSessTime);
		
		// ------------------------------------------

		int width = frame.getSize().width;
		int height = frame.getSize().height;

		// button to insert a new record in the ABS table
		JButton createSessBtn = new JButton("Crear");
		createSessBtn.setBounds(width/2-100/2, 135, 100, 25);
		createSessBtn.addActionListener(new ProcessCreaSessBtn(this));

		frame.add(createSessBtn);

		frame.setLocation(1920/2-width/2, 1080/2-height/2);
		frame.setVisible(true);
	}

	public JTextField getTxtNumSess() {
		return txtNumSess;
	}

	public void setTxtNumSess(JTextField txtNumSess) {
		this.txtNumSess = txtNumSess;
	}

	public JTextField getTxtCantRep() {
		return txtCantRep;
	}

	public void setTxtCantRep(JTextField txtCantRep) {
		this.txtCantRep = txtCantRep;
	}

	public JTextField getTxtSessTime() {
		return txtSessTime;
	}

	public void setTxtSessTime(JTextField txtSessTime) {
		this.txtSessTime = txtSessTime;
	}
}
