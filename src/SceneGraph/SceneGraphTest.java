package SceneGraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SceneGraphTest extends JFrame {

	private Scene component;
	
	public SceneGraphTest() {
		component = new Scene();
		
		this.setTitle("Scene Graph Test");
		this.setMinimumSize(new Dimension(220,150));
		/* Center this window in screen */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		int preferredWidth = (int)(screenWidth *0.7);
		int preferredHeight = (int)(screenHeight *0.7);
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		int preferredX = (screenWidth - preferredWidth) /2;
		int preferredY = (screenHeight - preferredHeight) /2;
		this.setLocation(preferredX, preferredY);
		/* Set maximum size to screen size */
		this.setMaximizedBounds(new Rectangle(screenWidth, screenHeight));
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(component, BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SceneGraphTest test = new SceneGraphTest();

		
	}

}
