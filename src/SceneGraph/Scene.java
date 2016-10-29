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
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(Color.RED); // DEBUG
		root.paint(g2);
	}
}
