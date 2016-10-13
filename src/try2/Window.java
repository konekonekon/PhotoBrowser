package try2;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Window extends JFrame {
	
	//Panel panel;
	private Menu menu;
	private ToolBar toolBar;
	private JLabel label;
	private PhotoComponent pc;
	private JScrollPane sp;
	
	
	public Window(){
		this.setTitle("Photo Browser");
		this.setMinimumSize(new Dimension(220,150));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		int preferredWidth = (int)(screenWidth *0.7);
		int preferredHeight = (int)(screenHeight *0.7);
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
		sp = new JScrollPane(pc);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.getVerticalScrollBar().setUnitIncrement(10);
		sp.getHorizontalScrollBar().setUnitIncrement(10);
		
		this.setJMenuBar(menu);	
		this.add(toolBar, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);
		//this.add(pc, BorderLayout.CENTER);
		this.add(sp, BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
	}
	
	
	
}
