package SceneGraph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Scene extends JComponent {

	private static final long serialVersionUID = -4404963349605969650L;
	private RootNode root;

	public RootNode getRoot() {
		return root;
	}

	public Scene() {
		root = new RootNode();
		setBackground(Color.GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground());
		root.paint((Graphics2D) g);
	}
}