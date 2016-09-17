package try1;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoBrowser {

	private JFrame frame;
	private JPanel myPanel;
	
	private void gui(){
		frame = new JFrame("Photo Browser");
		frame.setSize(600,600);
		
		myPanel = new JPanel();
		myPanel.setLayout(new BorderLayout());
		
		frame.add(myPanel);
		frame.setVisible(true);
	}
	
	
	private void showMenu(){
		
	}
	
	
	public static void main(String[] args) {
		PhotoBrowser pb = new PhotoBrowser();
		pb.gui();
		

	}

}
