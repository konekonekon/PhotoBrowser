package try2;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	JToggleButton tb;
	
	public ToolBar(){
		tb = new JToggleButton("Family");
		this.add(tb);
		tb = new JToggleButton("Vacation");
		this.add(tb);
		tb = new JToggleButton("School");
		this.add(tb);

		
		
	}
	
}
