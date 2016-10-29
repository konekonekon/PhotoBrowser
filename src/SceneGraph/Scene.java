package SceneGraph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public class Scene extends JComponent {
	
	private RootNode root;
	private ContainerNode container;
	private TextNode text;
	private ImageNode image;
	private PathNode path;
	private ShapeNode shape;
	
	public Scene() {		
		root = new RootNode();
		//root.addChidren(new TextNode("Hello", new Point(10,10)));
		
		container = new ContainerNode();
		text = new TextNode("Hello", new Point(10,10));
		container.addChidren(text);
		root.addChidren(container);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(Color.RED);
		root.paint(g2);
		
	}
}
