package try2;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	
	//Panel panel;
	private Menu menu;
	private ToolBar toolBar;
	private JLabel label;
	
	public Window(){
		this.setTitle("Photo Browser");
		this.setPreferredSize(new Dimension(500,500));
		this.setLocation(500, 100); //to centarize?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(220,150));
		this.setLayout(new BorderLayout());

		label = new JLabel();
		label.setText("Status bar");
		menu = new Menu(label);
		toolBar = new ToolBar(label);
		//panel = new Panel();
		
		this.setJMenuBar(menu);	
		this.add(toolBar, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}
	
	
	
}
