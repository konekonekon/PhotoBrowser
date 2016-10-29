package SceneGraph;

import java.awt.Graphics2D;
import java.util.*;

public abstract class Node {

	private List<Node> children;

	public Node() {
		children = new ArrayList<Node>();
	}

	public void add(Node n) {
		children.add(n);
	}

	public void remove(Node n) {
		children.remove(n);
	}

	//public abstract Rectangle getBounds();

	public void paint(Graphics2D g2) {
		paintNode(g2);
		for (Node child : children)
			child.paint(g2);
	}

	protected abstract void paintNode(Graphics2D g2);

}
