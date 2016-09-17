package try2;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	//Panel myPanel;
	Menu menu;
	ToolBar toolBar;
	JPanel panel;
	JLabel label;
	
	public Window(){
		this.setTitle("Photo Browser");
		//this.setSize(600,600);
		this.setPreferredSize(new Dimension(500,500));
		this.setLocation(500, 100); //to centarize?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(250,200));
		
		//myPanel = new Panel();

		Container contentPane = getContentPane();
		//contentPane.add(myPanel);
		
		contentPane.setLayout(new BorderLayout());
		panel = new JPanel();
		menu = new Menu();
		toolBar = new ToolBar();
		label = new JLabel("Status bar");
		
		panel.setLayout(new FlowLayout());
		panel.add(menu);
		panel.add(toolBar);		
		
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(label, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}
	
	
	
}
