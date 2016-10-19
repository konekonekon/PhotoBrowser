package Lab2;

import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener {

	private JToggleButton family, vacation, school;
	private JLabel status;
	
	public ToolBar(JLabel status){
		this.status = status;
		
		family = new JToggleButton("Family");
		family.addActionListener(this);
		this.add(family);
		
		vacation = new JToggleButton("Vacation");
		vacation.addActionListener(this);
		this.add(vacation);
		
		school = new JToggleButton("School");
		school.addActionListener(this);
		this.add(school);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == family){
			status.setText("'Family' photos.");
		}
		
		if(e.getSource() == vacation){
			status.setText("'Vacation' photos.");
		}
		
		if(e.getSource() == school){
			status.setText("'School' photos.");
		}
	}
	
}
