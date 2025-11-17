package newgym.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import newgym.controller.ProcessNewActData;

public class NewActWindow {
	
	private JComboBox<String> comboYear;
	private JComboBox<String> comboMonth;
	private JComboBox<String> comboDay;
	private JComboBox<String> comboHour1;
	private JComboBox<String> comboHour2;
	private JComboBox<String> comboMin1;
	private JComboBox<String> comboMin2;
	private int day;
	
	public NewActWindow() {
		// TODO Auto-generated constructor stub
		// -----------------------------------------------------------
		
		Long now = System.currentTimeMillis();
		Date date = new Date(now);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int mins = cal.get(Calendar.MINUTE);
		int more = 10+15; // to increase the item's "y" property

		JFrame f = new JFrame();
		f.setBounds(1920/2-300/2, 1080/2-240/2, 300, 240);
		f.setTitle("nueva actividad");
		f.getContentPane().setLayout(null);
		
		
		JLabel lblComboYear = new JLabel("Año");
		lblComboYear.setBounds(10, 5, 30, 20);
		comboYear = new JComboBox<String>();
		comboYear.setBounds(10, 15 + more - 15, 100, 20);
		comboYear.addItem("2025");
		
		JLabel lblComboMonth = new JLabel("Mes");
		lblComboMonth.setBounds(120, 5, 30, 20);
		comboMonth = new JComboBox<String>();
		comboMonth.setBounds(120, 15 + more - 15, 100, 20);

		// ----------------------------------------------
		String[] mon = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
				"Septiembre","Octubre","Noviembre","Diciembre"};

		
		int currMonth = month;
		for (int i=0; i < mon.length; i++) {
			if (i >= currMonth)
				comboMonth.addItem(mon[i]);
		}
		
		// Get the number of days in the selected month
		YearMonth yearMonthObject = YearMonth.of(2025, month+1);
		int daysInMonth = yearMonthObject.lengthOfMonth(); //28 

		JLabel lblComboDay = new JLabel("Día");
		lblComboDay.setBounds(230, 5, 30, 20);
		comboDay = new JComboBox<String>();
		comboDay.setBounds(230, 15 + more - 15, 60, 20);
		
		for (int i=1; i <= daysInMonth; i++) {
			if (i >= day)
			comboDay.addItem(i+"");
		}
		
		comboDay.addActionListener(new ActionListener() {	// to refresh hours and minutes
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedDay = Integer.parseInt(getComboDay().getSelectedItem().toString());
				fillHourCombo(comboHour1, selectedDay, hour);
				fillHourCombo(comboHour2, selectedDay, hour);
				fillMinuteCombo(comboMin1, selectedDay, mins);
				fillMinuteCombo(comboMin2, selectedDay, mins);
			}
		});
		
		// --------------------------------------------------		
		JLabel from = new JLabel("Desde");
		from.setBounds(10, 69 + more, 50, 20);
		
		JLabel to = new JLabel("Hasta");
		to.setBounds(10, 99 + more, 50, 20);
		
		// --------------------------------------------------
		JLabel lblComboHour = new JLabel("Hora");
		lblComboHour.setBounds(56, 50 + more, 50, 20);
		comboHour1 = creaHourCombo(56, 70 + more, 60, 20, hour);
		comboHour2 = creaHourCombo(56, 100 + more, 60, 20, hour);
		
		// --------------------------------------------------
		JLabel lblComboMin = new JLabel("Minutos");
		lblComboMin.setBounds(126, 50 + more, 60, 20);
		comboMin1 = creaMinCombo(126, 70 + more, 60, 20, mins);
		comboMin2 = creaMinCombo(126, 100 + more, 60, 20, mins);
		
		// ------------------------------------------------
		JButton btnNewActiv = new JButton("Agregar");
		btnNewActiv.setBounds(188, 170, 100, 25);
		
		// Button actionListener Event
		btnNewActiv.addActionListener(new ProcessNewActData(this));
		
		f.add(lblComboYear);
		f.add(comboYear);
		f.add(lblComboMonth);
		f.add(comboMonth);
		f.add(lblComboDay);
		f.add(comboDay);
		f.add(lblComboHour);
		f.add(from);
		f.add(comboHour1);
		f.add(comboHour2);
		f.add(lblComboMin);
		f.add(to);
		f.add(comboMin1);
		f.add(comboMin2);
		f.add(btnNewActiv);
		
		f.setVisible(true);
	}
	
	public int getSelectedMonth(JComboBox<String> combo) {
		int v=0;		
		String item = combo.getSelectedItem().toString();
		if (item.equals("Enero")) v = 0;
		if (item.equals("Febrero")) v = 1;
		if (item.equals("Marzo")) v = 2;
		if (item.equals("Abril")) v = 3;
		if (item.equals("Mayo")) v = 4;
		if (item.equals("Junio")) v = 5;
		if (item.equals("Julio")) v = 6;		
		if (item.equals("Agosto")) v = 7;
		if (item.equals("Septiembre")) v = 8;
		if (item.equals("Octubre")) v = 9;
		if (item.equals("Noviembre")) v = 10;
		if (item.equals("Diciembre")) v = 11;
		
		return v;
	}
	
	public void fillHourCombo(JComboBox<String> comboHour, int day, int hour) {
		String[] hours = {"00","01","02","03","04","05","06","07","08","09","10",
				"11","12","13","14","15","16","17","18","19","20","21","22","23"};
		
		comboHour.removeAllItems();
		
		for (int i=0; i < hours.length; i++) {
			if (day > this.day) {
				comboHour.addItem(hours[i]);
			} else {				
				if (i >= hour) {				
					comboHour.addItem(hours[i]);
				}
			}
		}
	}
	
	public void fillMinuteCombo(JComboBox<String> comboMin, int day, int mins) {	
		comboMin.removeAllItems();
		for (int i=0; i < 60; i++ ) {
			if (day > this.day) {
				if (i < 10) {
					comboMin.addItem("0"+i);
				} else {				
					comboMin.addItem(i+"");
				}
			} else {				
				if (i >= mins) {	
					if (i < 10) {
						comboMin.addItem("0"+i);
					} else {				
						comboMin.addItem(i+"");
					}
				}
			}
		}
	}
	
	public JComboBox<String> creaHourCombo(int x, int y, int width, int height, int hour) {
		JComboBox<String> comboHour = new JComboBox<String>();
		comboHour.setBounds(x, y, width, height);
		
		fillHourCombo(comboHour, day, hour);
		
		return comboHour;
	}
	
	public JComboBox<String> creaMinCombo(int x, int y, int width, int height, int mins) {
		JComboBox<String> comboMin = new JComboBox<String>();
		comboMin.setBounds(x, y, width, height);
		
		fillMinuteCombo(comboMin, day, mins);
		
		return comboMin;
	}

	public JComboBox<String> getComboYear() {
		return comboYear;
	}

	public void setComboYear(JComboBox<String> comboYear) {
		this.comboYear = comboYear;
	}

	public JComboBox<String> getComboMonth() {
		return comboMonth;
	}

	public void setComboMonth(JComboBox<String> comboMonth) {
		this.comboMonth = comboMonth;
	}

	public JComboBox<String> getComboDay() {
		return comboDay;
	}

	public void setComboDay(JComboBox<String> comboDay) {
		this.comboDay = comboDay;
	}

	public JComboBox<String> getComboHour1() {
		return comboHour1;
	}

	public void setComboHour1(JComboBox<String> comboHour1) {
		this.comboHour1 = comboHour1;
	}

	public JComboBox<String> getComboHour2() {
		return comboHour2;
	}

	public void setComboHour2(JComboBox<String> comboHour2) {
		this.comboHour2 = comboHour2;
	}

	public JComboBox<String> getComboMin1() {
		return comboMin1;
	}

	public void setComboMin1(JComboBox<String> comboMin1) {
		this.comboMin1 = comboMin1;
	}

	public JComboBox<String> getComboMin2() {
		return comboMin2;
	}

	public void setComboMin2(JComboBox<String> comboMin2) {
		this.comboMin2 = comboMin2;
	}

	//
}