package Lab2WithSceneGraph;

import java.awt.event.*;
import javax.swing.*;

public class ToolBar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JToggleButton family, vacation, school;
	private Performer performer;

	public ToolBar(Performer performer) {
		this.performer = performer;

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

	/* Call Performer section in class Window */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == family)
			this.performer.family();
		if (e.getSource() == vacation)
			this.performer.vacation();
		if (e.getSource() == school)
			this.performer.school();
	}
}