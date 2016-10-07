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
		this.setMinimumSize(new Dimension(220,150));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		int preferredWidth = (int)(screenWidth *0.8);
		int preferredHeight = (int)(screenHeight *0.8);
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		int preferredX = (screenWidth - preferredWidth) /2;
		int preferredY = (screenHeight - preferredHeight) /2;
		this.setLocation(preferredX, preferredY);
		this.setMaximizedBounds(new Rectangle(screenWidth, screenHeight));
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
