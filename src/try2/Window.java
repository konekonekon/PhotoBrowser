package try2;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	
	//Panel panel;
	private Menu menu;
	private ToolBar toolBar;
	private JLabel label;
	private PhotoComponent pc;
	
	public Window(){
		this.setTitle("Photo Browser");
		this.setPreferredSize(new Dimension(500,500));
		this.setLocation(500, 100); //to centarize?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(220,150));
		this.setLayout(new BorderLayout());
		//this.setDefaultLookAndFeelDecorated(true);
	    //this.getContentPane().setBackground(Color.BLUE);

		label = new JLabel();
		label.setText("Status bar");
		pc = new PhotoComponent();
		toolBar = new ToolBar(label);
		menu = new Menu(label, pc);
		
		this.setJMenuBar(menu);	
		this.add(toolBar, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);
		this.add(pc, BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	
	
	
}
